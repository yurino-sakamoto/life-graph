//package com.lifegraph.team20.edit.service;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//
//import com.lifegraph.team20.edit.models.ChildLifegraph;
//
////バリテーションチェックのクラス
//不要
//public class Validation extends ChildLifegraph{
////メモ
////バリデーション走らせるようのアノテーションを有効活用する
//
//	//目次
//	//①必須チェックを行う（）
//	//②データ型チェックをおこなう
//	//③文字の長さのチェック（年齢）
//
//	//疑問（@Entityのアノテーションは何のため？ダメなの？）
//
//	public Validation() {
////バリテーションの時にアノテーションつけてるんけど重複してない
////@Validated
//	//①型の一致の確認(Child→age、score)
//		if( age ===  || int score ===  ) {
//			//レスポンス400（データ型があっていません）
//			ResponseEntity<String> hello() {
//		        return ResponseEntity.ok("OK");
//		    }
//		}
//
//		//②必須項目が入っているかどうか（Parent→特になし、Child→age、score）
//		if( int age === null || int score === null ) {
//			//レスポンス400（必須項目が入っていません）
//			ResponseEntity<String> hello() {
//		        return ResponseEntity.ok("OK");
//		    }
//		}
//	}
//}
