package com.lifegraph.team20.security.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.lifegraph.team20.models.ChildGraph;
import com.lifegraph.team20.models.ParentGraph;
import com.lifegraph.team20.payload.request.RegisterRequest;
import com.lifegraph.team20.payload.response.Account;
import com.lifegraph.team20.payload.response.ChildGraphReference;
import com.lifegraph.team20.payload.response.SearchResponse;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;
import com.lifegraph.team20.repository.SearchRepository;
import com.lifegraph.team20.request.SearchGraphInfo;

@Service
@Transactional
public class LifeGraphService {

	@Autowired
	private ChildGraphRepository childRepository;
	@Autowired
	private ParentGraphRepository parentRepository;
	@Autowired
	private SearchRepository SearchRepository;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;


	/*
	 * 登録（参照）API
	 */

	// parentIdを持たせておく
	// ①親idを追加or更新
	// ②子供idを追加更新(idは自動挿入、parent_idで更新か)

	public void register(RegisterRequest request) {
		// parent
		registerParent(request);

		// children
		registerChildren(request);
	}

	//aboutParentId
	private void registerParent(RegisterRequest request) {
		ParentGraph parent;
		LocalDateTime now = LocalDateTime.now();

		//親テーブルがすでにある時
		System.out.println("親の分岐に入るよ");
		if (request.getParentId() != null) {
			// insert
			parent = parentRepository.findById(request.getParentId())
					.orElseThrow(() -> new IllegalStateException("record not found. parent id : " + request.getParentId()));
		} else {
			// create
			parent = new ParentGraph(request);
			parent.setCreatedAt(now);
		}
		parent.setUpdatedAt(now);
		//try catchで例外処理を返す
		try {
			parent = parentRepository.save(parent);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalStateException("already exits parent graph. user id : " + request.getUserId(), e);
		}

		// set parentId to request
		request.setParentId(parent.getId());
	}


	//aboutChildTable
	private void registerChildren(RegisterRequest request) {
		childRepository.deleteByParentId(request.getParentId());
		// convert request to entities
		List<ChildGraph> childrenEntities = request.getChildren().stream().map(child -> {
			return new ChildGraph(request.getParentId(), child);
		}).collect(Collectors.toList());
		childRepository.saveAll(childrenEntities);
	}

	/*
	 * 削除API
	 */
	public void deleteTable(long parentId) {
		childRepository.deleteByParentId(parentId);
		parentRepository.deleteById(parentId);
	}

	/*
	 * 検索API
	 */
	public List<SearchResponse> search(Optional<String> likeName, Optional<Date> startDate, Optional<Date> finishDate) {

		String sql = generateSqlForSearch(likeName, startDate, finishDate);

		List<SearchGraphInfo> resultSet = SearchRepository.searchGraphs(sql);
		return convertToResponse(resultSet);
	}

	private String generateSqlForSearch(Optional<String> likeName, Optional<Date> startDate, Optional<Date> finishDate) {
		String sql = "select `username`, parent_graphs.`id`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id ";
		StringJoiner sj = new StringJoiner(" AND ", " WHERE ", ";");

		if (likeName.isPresent()) {
			sj.add("username like '%" + likeName.get() + "%'");
		}

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (startDate.isPresent()) {
			sj.add(String.format("'%s' <= updated_at", sdFormat.format(startDate.get())));
		}

		if (finishDate.isPresent()) {
			Date tomorrowOfTo = addOneDay(finishDate.get());
			sj.add(String.format("updated_at < '%s'", sdFormat.format(tomorrowOfTo)));
		}

		if (likeName.isPresent() || startDate.isPresent() || finishDate.isPresent()) {
			sql += sj.toString();
		}

		return sql;
	}

	//calendarクラスで日時などの操作を行える。
	private Date addOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	private List<SearchResponse> convertToResponse(List<SearchGraphInfo> resultSet) {
		if (CollectionUtils.isEmpty(resultSet)) {
			return new ArrayList<>();
		}
		return resultSet.stream().map(result -> new SearchResponse(result)).collect(Collectors.toList());
	}

	/*
	 * アカウント参照API
	 */
	public Account selectUserInfo(Integer id) {
		final String sql = "select users.id, username, name from users inner join user_roles on users.id = user_roles.user_id\n"
				+ "inner join roles on roles.id = user_roles.role_id where users.id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Account selectInfo = jdbcTemplate.queryForObject(sql, param, new RowMapper<Account>() {
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Account(rs.getLong("id"), rs.getString("username"), rs.getString("name"));// nameはroleテーブルのname（権限）
			}
		});
		return selectInfo;
	}

	/*
	 * 人生グラフ参照API
	 */

	public List<ChildGraphReference> selectChildRef(Integer userId) {
		final String sql = "select * from child_graphs where parent_id = (select id from parent_graphs where user_id = :userId);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<ChildGraphReference> selectChildInfo = jdbcTemplate.query(sql, param, new RowMapper<ChildGraphReference>() {
			public ChildGraphReference mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new ChildGraphReference(rs.getLong("id"), rs.getLong("parent_id"), rs.getInt("age"), rs.getInt("score"),
						rs.getString("comment"));
			}
		});
		return selectChildInfo;
	}
}