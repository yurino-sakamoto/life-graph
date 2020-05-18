//package com.lifegraph.team20.searchdelate;
//pa
//
//
//
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