package com.lifegraph.team20.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.LifeGraphSearchService;
import com.lifegraph.team20.payload.response.SearchResponse;

@RestController
public class SearchController {

	@Autowired
	  private LifeGraphSearchService service;

	//Vueから@RequestParamで受け取る。@DateTimeFormatで日時の受け取り方を設定する。
	@GetMapping("/life-graphs")
	  public ResponseEntity<List<SearchResponse>> SearchGraphInfos(@RequestParam("likeName") Optional<String> likeName,
	      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Optional<Date> startDate,
	      @RequestParam("finishDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Optional<Date> finishDate) {

	    List<SearchResponse> res = service.search(likeName, startDate, finishDate);
	    //JSON形式で返す
	    return ResponseEntity.ok(res);
	  }
}

//ほぼ完成
//package com.lifegraph.team20.controller;
//
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import java.util.StringJoiner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lifegraph.team20.request.SearchGraphInfo;
//
//@RestController
//public class SearchController {
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public ResponseEntity<List<SearchGraphInfo>> SearchGraphInfos(@RequestParam("likeName") Optional<String>likeName,
//			@RequestParam("startDate") Optional<String>startDate,
//			@RequestParam("finishDate") Optional<String>finishDate){
//		List<SearchGraphInfo> SearchGraphInfos = SelectSearchGraphInfo(likeName, startDate, finishDate);
//		return ResponseEntity.ok(SearchGraphInfos);
//	}
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	private List<SearchGraphInfo> SelectSearchGraphInfo(Optional<String> likeName, Optional<String>startDate, Optional<String> finishDate) {
//		String sql =  "select `username`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id ";
//		StringJoiner sj = new StringJoiner(" and ", " where ", ";");
//
//		if(likeName.isPresent()) {
//			sj.add("username like '%" + likeName.get() + "%'");
//		}
//
//		if (startDate.isPresent()) {
//			sj.add(String.format("updated_at >=" + startDate.get()));
//		}
//
//		if (finishDate.isPresent()) {
//			sj.add(String.format("updated_at <" + finishDate.get()));
//		}
//
//		if (likeName.isPresent() || startDate.isPresent() || finishDate.isPresent()) {
//			sql += sj.toString();
//		}
//
//		return jdbcTemplate.query(sql,new RowMapper<SearchGraphInfo>() {
//			public SearchGraphInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new SearchGraphInfo(rs.getString("username"), rs.getLong("user_id"),rs.getTimestamp("created_at"),rs.getTimestamp("updated_at"));
//			}
//		});
//	}
//}