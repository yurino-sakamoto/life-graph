//package com.lifegraph.team20.edit.service;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lifegraph.team20.edit.models.ChildLifegraph;
//import com.lifegraph.team20.edit.models.ParentsLifegraph;
//import com.lifegraph.team20.edit.repository.ChildRepository;
//import com.lifegraph.team20.edit.repository.ParentsRepository;
//
////Service→ビジネスロジック
////案①
////→void register() 登録・編集を行うメソッド
////親の登録、更新を行う
////→parent_id有無判定
////　無：insertParent
////　有：updateParent
//
////子の登録・更新を行う
////→exists有無判定
////　無：insertChild
////　有：updateChild
//
////案②ageで恕軒分岐する場合（採用）
////親の登録更新判定（parents_idの有無）
////無：insertParent
////有：updateParent
//
////子のデータ判定（ageのデータを見る）
////上書きする：addChild();
////エラーを返す：Httpリクエスト400
////新規登録：addChild();
//
////案③ageで恕軒分岐しない場合（採用）
////親の登録更新判定（parents_idの有無）
////無：insertParent
////有：updateParent
//
////子のデータ判定（ageのデータを見る）
////上書きする：addChild();
////エラーを返す：Httpリクエスト400
////新規登録：addChild();
//
//@Service
//public class ExsistService extends ParentsLifegraph{
//
//	@Autowired
//	ParentsRepository ParentsRepository;
//	ChildRepository ChildRepository;
//
//	//これいる？？？
//	//	public LifeGraphService(long user_id, Timestamp createdAtTime, Timestamp updatedAtTime) {
//	//	super(user_id, createdAtTime, updatedAtTime);
//	//	// TODO 自動生成されたコンストラクター・スタブ
//	//}
//
//	//		ageで条件分岐する場合の案
//	//インサートの条件分岐
//	//メソッドの中に入れる必要あり？？と思ったから書き換えた！(コンストラクタを定義)
//	public void exsist() {
//		//親テーブルに情報がないとき。exsistById()→booleanで帰るjpaのメソッド
//		if(ParentsRepository.exsistById()) {
//			//	 ページから取得したidと同じかどうかを確認する
//
//			String mySql = “insert into parent_chart (update_at) values (‘” + getNowDateTime() + “‘)“;
//			stmt.executeUpdate(mySql);
//
//			//①親テーブルに新規時間
//			insertParents(〇〇);
//
//			//②小テーブルに追加
//			addChild(〇〇);
//
//			//子テーブルの年齢が被っていない時（ageで情報が被っているかを判断する）
//		}else {
//
//			//親テーブルの登録日時を更新
//			String mySql = “insert into parent_chart (created_at) values (‘” + getNowDateTime() + “’)“;
//			stmt.executeUpdate(mySql);
//
//			//ageの有無で分岐させる
//			if(ChildRepository.exsistByAge()) {
//				ChildRepository. queryAll() ;
//
//				public LifeGraph delete(LifeGraph lifeGraph) {
//
//					ParentsLifeGraph.getLifeGraph().clear();
//
//					ParentsLifeGraph.getLifeGraph().add(lifeGraph);
//
//					return lifeGraphRepository.save(lifeGraph);
//
//				}else {
//					addChild();
//				}
//			}
//		}
//
//		//saveとsaveAndFlushの違いは特にない（）
//		//小テーブルの情報を追加or新規登録(メッシと違う)
//		public ChildLifegraph addChild(ChildLifegraph lifegraph){
//			return ChildRepository.save(lifegraph);
//		}
//
//		//タイムスタンプの押し方を考えなくてはならない（どうやって、片方のみに押すのか）
//		//親テーブルの新規登録
//		public String insertParents(ParentsLifegraph lifegraph){
//			final DateFormat df = new SimpleDateFormat(“yyyy-MM-dd HH:mm:ss”);
//			final Date date = new Date(System.currentTimeMillis());
//			return df.format(date);
//		}
//		//	//親テーブル更新（不必要）
//		//	public ParentsLifegraph updateParents(ParentsLifegraph lifegraph){
//		//		return ParentsRepository.save(lifegraph);
//		//	}
//
////		//小テーブルのレコードを削除する
////		public ParentsLifegraph deleteChild(ParentsLifegraph lifegraph){
////			//①SQLからデータを持ってくる（レコード全部の方が楽？カラムの方が楽？→レコードを採用）
////			@Query()
////
////			//②
////
////			return ParentsRepository.save(lifegraph);
////		}
//	}
//
//
//	//ageで条件分岐する場合の案
//	////インサートの条件分岐
//	////メソッドの中に入れる必要あり？？と思ったから書き換えた！(コンストラクタを定義)
//	//public InsertService() {
//	////親テーブルに情報がないとき
//	//if(user_id==null) {
//	////①親テーブルに新規登録時間
//	//this.insertParents(lifegraph);
//	////②小テーブルに追加
//	//this.addChild(null);
//	////子テーブルの年齢が被っていない時（ageで情報が被っているかを判断する）
//
//	//}else if(user_id != null && age == null) {
//	////①親テーブルの登録日時を更新
//	//this.insertParents(lifegraph);
//	////②小テーブル追加
//	//this.addChild(null);
//	//
//	////小テーブルの年齢情報が被っている時
//	//}else {
//	////①小テーブルの内容を削除
//	////②小テーブルにデータを登録
//	//}
