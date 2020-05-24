//package com.lifegraph.team20.repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import com.lifegraph.team20.request.SearchGraphInfo;
//
//@Repository
//public class SearchRepository {
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	public List<SearchGraphInfo> searchGraphs(String sql) {
//		return jdbcTemplate.query(sql, new RowMapper<SearchGraphInfo>() {
//			public SearchGraphInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new SearchGraphInfo(rs.getString("username"), rs.getLong("user_id"),rs.getTimestamp("created_at"),
//						rs.getTimestamp("updated_at"));
//			}
//		});
//	}
//}

