package com.lifegraph.team20.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChildLifeGraphRepository {



	// データベースと接続する
	@Autowired
	// MySQLのデータを持ってくるライブラリ
	JdbcTemplate jdbcTemplate;
	public Boolean existsByUserIdAndAge(long parentId, int age) {
		final String sql = "select count(*) from child_chart where parent_id = " + parentId + " age = " + age;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return 1 <= count;
	}
	// ageが重複しているか確認したい
	// リクエストできたageが同じユーザーのレコードに既に存在しているか
	public Integer selectChild(int age) {
		final String sql = "select count(*) from child_chart where age = " + age;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	// 子テーブルのレコードを編集する
	// IDを持つ必要がある？？
	public void updateChild(long id, int age, int score, String comment) {
		jdbcTemplate.update( "update child_chart set age = " + age + ", score = " + score + ", comment = " + comment + "where id  = "+ id );
	}
	// 子テーブルにレコードを追加する
	public void addChild(long parentId, int age, int score, String comment) {
		jdbcTemplate.update("insert into child_chart (parent_id, age, score, comment) values (" + parentId + "," + age + ","
				+ score +  "," + comment + ")");
	}
}