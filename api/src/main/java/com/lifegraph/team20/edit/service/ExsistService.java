package com.lifegraph.team20.edit.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.edit.Controller.LifeGraphdata;
import com.lifegraph.team20.edit.models.ChildLifegraph;
import com.lifegraph.team20.edit.models.ParentsLifegraph;
import com.lifegraph.team20.edit.repository.ParentsRepository;
import com.lifegraph.team20.edit.repository.ChildRepository;

//Service→ビジネスロジック
//案①
//→void register() 登録・編集を行うメソッド
//親の登録、更新を行う
//→parent_id有無判定
//　無：insertParent
//　有：updateParent

//子の登録・更新を行う
//→exists有無判定
//　無：insertChild
//　有：updateChild

//案②ageで恕軒分岐する場合（採用）
//親の登録更新判定（parents_idの有無）
//無：insertParent
//有：updateParent

//子のデータ判定（ageのデータを見る）
//上書きする：addChild();
//エラーを返す：Httpリクエスト400
//新規登録：addChild();

//案③ageで恕軒分岐しない場合（採用）
//親の登録更新判定（parents_idの有無）
//無：insertParent
//有：updateParent

//子のデータ判定（ageのデータを見る）
//上書きする：addChild();
//エラーを返す：Httpリクエスト400
//新規登録：addChild();

@Service
public class ExsistService extends ParentsLifegraph {
	@Autowired
	ParentsRepository ParentsRepository;
	ChildRepository ChildRepository;

	//	//これがあるのはおかしくない？？↓
	//	public ExsistService(long id, Timestamp update_at, long user_id) {
	//		super(id, update_at, user_id);
	//		// TODO 自動生成されたコンストラクター・スタブ


	//ageで条件分岐する場合の案
	//インサートの条件分岐
	//メソッドの中に入れる必要あり？？と思ったから書き換えた！(コンストラクタを定義)
	public void exsist(LifeGraphdata lifegraph) {

		//親テーブルに情報がないとき。exsistById()→booleanで帰るjpaのメソッド
		if(ParentsRepository.existsById(lifegraphからIdを取り出して入れたい) == false) {


			//①親テーブルに新規時間
			//タイムスタンプを押す実行文をmySQLおく
			String mySql = "insert into parent_chart (update_at) values (‘” + getNowDateTime() + “‘)";
			//ステートメントっていうインターフェースのメソッドを呼び出す→インサート文の実行
			//インサート文を流したい
			stmt.executeUpdate(mySql);

			//②小テーブルに追加
			addChild(lifegraph);

			//子テーブルの年齢が被っていない時（ageで情報が被っているかを判断する）
		}else {

			//①親テーブルの登録日時を更新
			String mySql = "insert into parent_chart (created_at) values (‘” + getNowDateTime() + “’)";
			stmt.executeUpdate(mySql);

			//②ageの有無で分岐させる
			if(ChildRepository.existsByAge(lifegraphから年齢を取り出して入れたい) == true) {
				//既に登録済みのものがある場合（年齢で）
				ChildRepository.queryAll();

				public ChildLifegraph delete(ChildLifegraph ChildLifegraph) {
					//詰める必要あり
					//親テーブルに残っているデータを消す
					ParentsLifegraph.getChildLifegraph().clear(Entityからの情報);

					//親テーブルに小テーブルの情報を追加する
					ParentsLifegraph.getLifegraph().add(Entityからの情報);

					//データをセーブする
					return lifeGraphRepository.save(Entityの情報));

				}else {
					//新規で登録する場合（年齢にかぶりがない場合）
					addChild(Vueからの情報);
				}
			}
		}

		//saveとsaveAndFlushの違いは特にない（）
		//小テーブルの情報を追加or新規登録(メッシと違う)
		public ChildLifegraph addChild(ChildLifegraph childEntity){
			return ChildRepository.save(childEntity);
		}

		//タイムスタンプの押し方を考えなくてはならない（どうやって、片方のみに押すのか）
//		//親テーブルの新規登録
//		public String insertParents(ParentsLifegraph ParentsEntity){
//			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			final Date date = new Date(System.currentTimeMillis());
//			return df.format(date);
//		}
		//	//親テーブル更新（不必要）
		//	public ParentsLifegraph updateParents(ParentsLifegraph lifegraph){
		//		return ParentsRepository.save(lifegraph);
		//	}

		//		//小テーブルのレコードを削除する
		//		public ParentsLifegraph deleteChild(ParentsLifegraph lifegraph){
		//			//①SQLからデータを持ってくる（レコード全部の方が楽？カラムの方が楽？→レコードを採用）
		//			@Query()
		//
		//			//②
		//
		//			return ParentsRepository.save(lifegraph);
		//		}

	}


	//ageで条件分岐する場合の案
	////インサートの条件分岐
	////メソッドの中に入れる必要あり？？と思ったから書き換えた！(コンストラクタを定義)
	//public InsertService() {
	////親テーブルに情報がないとき
	//if(user_id==null) {
	////①親テーブルに新規登録時間
	//this.insertParents(lifegraph);
	////②小テーブルに追加
	//this.addChild(null);
	////子テーブルの年齢が被っていない時（ageで情報が被っているかを判断する）

	//}else if(user_id != null && age == null) {
	////①親テーブルの登録日時を更新
	//this.insertParents(lifegraph);
	////②小テーブル追加
	//this.addChild(null);
	//
	////小テーブルの年齢情報が被っている時
	//}else {
	////①小テーブルの内容を削除
	////②小テーブルにデータを登録
	//}
