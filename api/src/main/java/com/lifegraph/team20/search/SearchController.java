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
//	@Autowired
//	public SearchRepository repository;
//
//	@RequestMapping(method = RequestMethod.GET)
//	List<Search> getSearch() {
//		return repository.getSearch();
//	}
//}


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
