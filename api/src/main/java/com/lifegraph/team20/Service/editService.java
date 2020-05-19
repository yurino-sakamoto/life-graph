//package com.lifegraph.team20.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import com.lifegraph.team20.repository.Childtable;
//import com.lifegraph.team20.repository.Parentstable;
//
//@Service
//public class editService {
//	@Autowired
//	private static JdbcTemplate jdbcTemplate;
//	public static void exists(long userId, int age) {
//		//親テーブルに情報があるかチェックするメソッドを呼び出す
//		userIdCheck(userId);
//		ageChack(age);
//	}
//	//親テーブルに情報があるかどうかのチェック
//	public static void userIdCheck(long userId) {
//		//親グラフのuser_idの有無
//		//ある場合は何もしない
//		//ない場合はレコードを追加する
//		Integer count = Parentstable.selectParents(userId);
//		if (count == 0) {
//			//親テーブルにレコードを追加する処理
//			jdbcTemplate.update("\"INSERT INTO users (name, age) VALUES (?, ?)\", name, age");
//		}
//		//count=1ならuser_idが存在している→親テーブルに登録する必要なし
//	}
//	//子テーブルに同じageの情報があるかどうかのチェック
//	public static void ageChack(int age) {
//		Integer count = Childtable.selectChild(age);
//		if (count == 0) {
//			//ageが重複していない
//			//子テーブルにレコードを追加する処理
//		} else {
//			//ageが重複している
//			//更新をする処理
//		}
//	}
//}