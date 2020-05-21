//package com.lifegraph.team20.controller;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lifegraph.team20.payload.request.SearchGraphInfo;
//
//@RestController
//public class SearchController {
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//
//	public ResponseEntity<List<SearchGraphInfo>> SearchGraphInfos(@RequestParam("LikeName") Optional<String> like_name, @RequestParam("start_date") Optional<String>start_date, @RequestParam("update_date") Optional<String> update_date){
//		List<SearchGraphInfo> SearchGraphInfos = SelectSearchGraphInfo(like_name, start_date, update_date);
//		return ResponseEntity.ok(SearchGraphInfos);
//	}
//
//	@Autowired
//	private NamedParameterJdbcTemplate jdbcTemplate;
//	private List<SearchGraphInfo> SelectSearchGraphInfo(Optional<String> like_name, Optional<String> start_date, Optional<String> update_date) {
//		String sql="";
//		if(like_name.isPresent()) {
//			final String sql1 =  "select `username`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id where `username` like '%\"+like_name+\"%'";
//			sql=sql1;
//		}else if(start_date.isPresent() && update_date.isPresent()) {
//			final String sql2 =  "select `username`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id where \n" +
//					"(`created_at` like '%\"+start_date+\"%') AND (`updated_at` like '%\"+update_date+\"%')";
//			sql=sql2;
//		}
//		return jdbcTemplate.query(sql,new RowMapper<SearchGraphInfo>() {
//			public SearchGraphInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new SearchGraphInfo(rs.getString("name"), rs.getInt("user_id"),rs.getTimestamp("created_at"),rs.getTimestamp("updated_at"));
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
