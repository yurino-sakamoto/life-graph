//package com.lifegraph.team20.Service;
//package com.lifegraph.team20.Service;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lifegraph.team20.models.LifeGraphdata;
//import com.lifegraph.team20.models.ParentsLifegraph;
//import com.lifegraph.team20.repository.ParentsRepository;
//
//
//
//@Service
//public class LifegraphService extends ParentsLifegraph {
//	public void exsist(LifeGraphdata lifegraph) {
//
//		//親テーブルに情報がないとき。exsistById()→booleanで帰るjpaのメソッド
//		if(ParentsRepository.checkid(lifegraph.getId()) == false) {
//
//
//			//①親IDを登録（登録日時顔される）
//
//
//			//②小テーブルに追加
//			addChild(lifegraph);
//
//			//親テーブルに情報があるとき
//		}else {
//
//			//①レコードを更新する（親IDを更新）
//
//
//			//②ageの有無で分岐させる
//			if(ChildRepository.existsByAge(lifegraph.getage()) == true) {
//
//				//更新する
//				ChildRepository.save(lifegraph);
//
//
//				}else {
//					//新規で登録する場合（年齢にかぶりがない場合）
//					addChild(lifegraph);
//				}
//			}
//		}
//		//saveとsaveAndFlushの違いは特にない（）
//		//小テーブルの情報を追加or新規登録(メッシと違う)
//		public ChildLifegraph addChild(ChildLifegraph childEntity){
//			return ChildRepository.save(childEntity);
//		}
//
//		public ChildLifegraph addParent(ChildLifegraph childEntity){
//			return ChildRepository.save(childEntity);
//		}