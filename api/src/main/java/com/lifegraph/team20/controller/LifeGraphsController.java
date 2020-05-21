package com.lifegraph.team20.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.LifeGraphService;
import com.lifegraph.team20.payload.request.RegisterRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsController {

  @Autowired
  private LifeGraphService service;

  // 保存（APIで使い分ける 問題点：1テーブル）
  @PostMapping(value = "/new")
  public ResponseEntity<Void> postContent(@RequestBody @Valid RegisterRequest request) {

    service.register(request);

    return ResponseEntity.created(null).build();
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
//
////    //削除（現状、問題点：1テーブルのみ）
////    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
////    public ResponseEntity<?> postContent(@RequestBody ResisterRequest resisterRequest) {
////
////
////    	//管理者権限以上
////    	//テーブル全部削除
////    	contentService.deleteContent(resisterRequest.getParent_id());
////
////    return (ResponseEntity<?>) ResponseEntity.ok();
////    }
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