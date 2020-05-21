package com.lifegraph.team20.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.LifeGraphService;
import com.lifegraph.team20.payload.request.RegisterRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LifeGraphsController {

	@Autowired
	private LifeGraphService service;

	// 保存（APIで使い分ける 問題点：1テーブル）
	@PostMapping(value = "/life-graphs")
	public ResponseEntity<Void> postContent(@RequestBody @Valid RegisterRequest request) {
		service.register(request);

		//TODO URL指定をする
		return ResponseEntity.created(null).build();
	}

	//TODO 管理者権限関連の設定
	//パスパラメータを受け取れていない（/1のこと パスバリアブルでidを取得すると1が返却（調べる））
	//削除（現状、問題点：1テーブルのみ）
	@DeleteMapping(value = "/{parentId}")
	public ResponseEntity<Void> deleteTable(@PathVariable("parentId") long parentId) {
//		パスバリアブル
		//デリートの手順
		//持っておいて欲しいもの（user_id,Authority）
		//①
		//②
		//管理者権限以上・削除などののビジネスロジックを実行↓↓
		service.deleteTable(parentId);
		return ResponseEntity.noContent().build();
	}
}




//    //保存（APIで使い分ける　問題点：1テーブル）
//    @RequestMapping(value = "/new",method = RequestMethod.POST)
//    Parentdata postContent(@RequestBody Parentdata parentdata) {
//    	return contentService.postContent(parentdata);
//    }

//  // 見るやつ（いらん）
//  @RequestMapping(value = "/a", method = RequestMethod.GET)
//  List<ChildGraph> getContent() {
//    //
//    return contentService.getContent();
//  }
//
//  // 更新（APIで使い分ける）
//  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//  ChildGraph putTweet(@PathVariable("id") Integer id, @RequestBody ChildGraph content) {
//    content.setId(id);
//    return contentService.updateContent(content);
//  }
//}
//
////新規登録：ボタンが押される
////親テーブルと小テーブルの情報が追加（/new）
////親テーブル：user_id
////小テーブル：id、parent_id、comment、age、score
//
////変更の場合（/{id}）
////親テーブル：user_id
////小テーブル：id、parent_id、comment、age、score