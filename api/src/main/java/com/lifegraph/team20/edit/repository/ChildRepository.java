package com.lifegraph.team20.edit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.edit.models.ChildLifegraph;
//
////データベース接続する系
//
////→exists() 親ID、年齢ですでにレコードが存在するか
////→insertChild()
////→updateChild()
////→insertParent()
////→updateParent()
//
@Repository
public interface ChildRepository extends JpaRepository<ChildLifegraph, Long> {
	//この中には書き込まない（入れるためのメソッドがJpaRepositoryで用意されているから）

	//と思ったけどやっぱやめ笑

	// ageがすでに登録された物が存在しているかを判断する
	 Boolean existsByAge(int age);

	 //
     @Query("SELECT * FROM child_chart WHERE parent_id :parent_id and age :age")
     //queryAllは使って大丈夫なの？メソッドある？
     List<ChildLifegraph> queryAll();
}