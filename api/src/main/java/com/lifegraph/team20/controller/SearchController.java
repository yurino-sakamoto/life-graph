//package com.lifegraph.team20.controller;
//
//
//import java.sql.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lifegraph.team20.Service.LifeGraphSearchService;
//
//@RestController
//public class SearchController {
//
//	@Autowired
//	private LifeGraphSearchService service;
//
//	@GetMapping("/search")
//	public ResponseEntity<List<SearchResponse>> SearchGraphInfos(@RequestParam("likeName") Optional<String>likeName,
//			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date>startDate,
//			@RequestParam("finishDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date>finishDate) {
//
//		List<SearchResponse>  SearchGraphInfo = service.search(likeName, startDate, finishDate);
//
//	    return ResponseEntity.ok(SearchGraphInfo);
//}


//@RestController
//public class SearchController {
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public ResponseEntity<List<SearchGraphInfo>> SearchGraphInfos(@RequestParam("likeName") Optional<String>likeName, @RequestParam("startDate")Optional<String>startDate, @RequestParam("finishDate")Optional<String>finishDate){
//		List<SearchGraphInfo> SearchGraphInfos = SelectSearchGraphInfo(likeName, startDate, finishDate);
//		return ResponseEntity.ok(SearchGraphInfos);
//	}
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	private List<SearchGraphInfo> SelectSearchGraphInfo(Optional<String> likeName, Optional<String>startDate, Optional<String> finishDate) {
//		String sql =  "select `username`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id ";
//		StringJoiner sj = new StringJoiner("and", "where", ";");
//		if(likeName.isPresent()) {
//			sql += "where `username` like '%"+likeName.get()+"%'";
//		}if(likeName.isPresent() || startDate.isPresent()) {
//			sql += "where `username` like '%"+likeName.get()+"%' AND `updated_at` >= '+startDate.get()+'";
//		}if(likeName.isPresent() || finishDate.isPresent()) {
//			sql += "where `username` like '%"+likeName.get()+"%' AND `updated_at` <= '+finishDate.get()+'";
//		}if(likeName.isPresent() || startDate.isPresent() || finishDate.isPresent()) {
//			sql += "where `username` like '%"+likeName.get()+"%' AND `updated_at` >= '+startDate.get()+' AND `updated_at` <= '+finishDate.get()+'";
//		}if(startDate.isPresent()) {
//			sql += "WHERE `updated_at` >= '+startDate.get()+' ";
//		}if(finishDate.isPresent() ) {
//			sql += "WHERE `updated_at` <= '+updateDate.get()+'";
//		}if(startDate.isPresent() || finishDate.isPresent()) {
//			sql += "WHERE `updated_at` >= '+startDate.get()+' AND `updated_at` <= '+finishDate.get()+'";
//		}
////		if (likeName.isPresent()) {
////			sj.add("username = '%" + likeName + "%'");
////		}
////		if (updateDate.isPresent()) {
////			sj.add(updateDate + " <= 'updated_at'");
////		}
////		if (updateDate.isPresent()) {
////			sj.add("'updated_at' <= " + updateDate);
////		}
////		sql += sj.toString();
//		return jdbcTemplate.query(sql,new RowMapper<SearchGraphInfo>() {
//			public SearchGraphInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new SearchGraphInfo(rs.getString("username"), rs.getInt("user_id"),rs.getTimestamp("created_at"),rs.getTimestamp("updated_at"));
//			}
//		});
//	}
//}


//②成功
//package com.lifegraph.team20.search;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@ComponentScan
////@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/search")
//public class SearchController {
//
//
////	private static final String VIEW = "/search";
//
//	@Autowired
//	public SearchService service;
//
//	@RequestMapping(method = RequestMethod.GET)
//	List<Search> getSearch() {
//		return service.getSearch();
//	}
//
//	List<parent_graph> getGraph() {
//		return service.getGraph();
//	}
//}

//①
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView login(ModelAndView mav
//			,@RequestParam("userId") String userId
//			,@RequestParam("updatedAt") String updatedAt) {
//
//		mav.setViewName(VIEW);
//		mav.addObject("userId", userId);
//		mav.addObject("updatedAt", updatedAt);
//		List<Search> result = service.search(userId,updatedAt);
//		mav.addObject("result", result);
//		mav.addObject("resultSize", result.size());
//		return mav;
//	}



//@Autowired
//
//private JdbcTemplate jdbcTemplate;
//
//@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public  List<Search> search() {
//		List<Search> searchDatas = setSearch();
//		return searchDatas;
//}
//
//private List<Search> setSearch() {
//	final String sql = "select * users inner join parent_graph on users.id = parent_graph.user_id";
//	return jdbcTemplate.query(sql, new RowMapper<Search>() {
//		public Search mapRow(ResultSet rs, int ronNum) throws SQLException{
//			return new Search(rs.getString("usename"), rs.getTimestamp("updated_at"));
//		}
//	});
//}
