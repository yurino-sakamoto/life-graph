package com.lifegraph.team20.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Childtable {
	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private static JdbcTemplate jdbcTemplate;

	//ageが重複しているか確認したい
	//リクエストできたageが同じユーザーのレコードに既に存在しているか
	public static Integer selectChild(int age) {
		final String sql = "select count(*) from child_graphs where age = " + age;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
}
