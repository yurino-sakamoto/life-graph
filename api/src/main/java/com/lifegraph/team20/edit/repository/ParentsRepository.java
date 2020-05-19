//package com.lifegraph.team20.edit.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.lifegraph.team20.edit.models.ParentsLifegraph;
//
//
//
////データベース接続する系
////→exists() 親ID、年齢ですでにレコードが存在するか
////→insertChild()
////→updateChild()
////→insertParent()
////→updateParent()
////本当はこれらを中に入れたほうがいい↑なんかバグるから一旦（Serviceに入れる。あとで調節。）
//
//@Repository
//public interface ParentsRepository extends JpaRepository<ParentsLifegraph, Long> {
//	// ageがすでに登録された物が存在しているかを判断する物
//	//書き方が怪しい
//    Boolean existsById(long id);
//
//    @Query("SELECT * FROM child_chart WHERE parent_id :parent_id and age :age")
//    List<ParentsLifegraph> queryAll();
//
//}