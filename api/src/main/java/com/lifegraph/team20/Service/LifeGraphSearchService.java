//package com.lifegraph.team20.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.StringJoiner;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LifeGraphSearchService {
//
//	@Autowired
//	private SearchRepository searchRepository;
//
//	public List<SearchGraphInfo> SelectSearchGraphInfo(Optional<String> likeName, Optional<Date> startDate, Optional<Date> finishDate) {
//
//	String sql = generateSqlForSearch(likeName, startDate, finishDate);
//
//	List<SearchGraphInfo> searchGraphInfos = SearchRepository.
//
//	}
//
//
//
//	private String generateSqlForSearch(Optional<String> likeName, Optional<Date> startDate, Optional<Date> finishDate) {
//		String sql = "select `username`, `user_id`, parent_graphs.`created_at`, parent_graphs.`updated_at` from users INNER JOIN parent_graphs on users.id = parent_graphs.user_id ";
//		StringJoiner sj = new StringJoiner(" AND ", " WHERE ", ";");
//
//		if (likeName.isPresent()) {
//			sj.add("u.username like '%" + likeName.get() + "%'");
//		}
//
//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//		if (startDate.isPresent()) {
//			sj.add(String.format("'%s' <= p.updated_at", sdFormat.format(startDate.get())));
//		}
//
//		if (finishDate.isPresent()) {
//			Date tomorrowOfTo = addOneDay(finishDate.get());
//			sj.add(String.format("p.updated_at < '%s'", sdFormat.format(tomorrowOfTo)));
//		}
//
//		if (likeName.isPresent() || startDate.isPresent() || finishDate.isPresent()) {
//			sql += sj.toString();
//		}
//
//		return sql;
//	}
//
//	private Date addOneDay(Date date) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, 1);
//		return calendar.getTime();
//	}
//
//	private List<SearchResponse> convertToResponse(List<SearchResultSet> resultSet) {
//	 if (CollictionUtils.isEmpty(resultSet)) {
//		 return new ArrayList<>();
//	 }
//	 return resultSet.stream().map(result -> new SearchResponse(result)).collect(Collectors.toList());
//   }
//}