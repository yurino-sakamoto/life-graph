package com.lifegraph.team20.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.ContentService;
import com.lifegraph.team20.models.Child_graphs;
import com.lifegraph.team20.models.Parent_graphs;
import com.lifegraph.team20.payload.request.ResisterRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {

	@Autowired
    ContentService contentService;
	ResisterRequest resisterRequest;

	//保存（APIで使い分ける　問題点：1テーブル）
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ResponseEntity<Void> postContent(@RequestBody ResisterRequest resisterRequest) {
//    	Child_graphs childlifegraph = new Child_graphs(resisterRequest);
    	Parent_graphs parentlifegraph = new Parent_graphs(resisterRequest);

    	contentService.postContent(parentlifegraph);
//    	contentService.postContent(childlifegraph);

        return ResponseEntity.created(null).build();
    }

//    //保存（APIで使い分ける　問題点：1テーブル）
//    @RequestMapping(value = "/new",method = RequestMethod.POST)
//    Parentdata postContent(@RequestBody Parentdata parentdata) {
//    	return contentService.postContent(parentdata);
//    }

    //見るやつ（いらん）
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    List<Child_graphs> getContent() {
    	//
        return contentService.getContent();
    }

    //更新（APIで使い分ける）
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Child_graphs putTweet(@PathVariable("id") Integer id, @RequestBody Child_graphs content) {
    	content.setId(id);
        return contentService.updateContent(content);
    }

//    //削除（現状、問題点：1テーブルのみ）
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> postContent(@RequestBody ResisterRequest resisterRequest) {
//
//
//    	//管理者権限以上
//    	//テーブル全部削除
//    	contentService.deleteContent(resisterRequest.getParent_id());
//
//    return (ResponseEntity<?>) ResponseEntity.ok();
//    }
}


//新規登録：ボタンが押される
//親テーブルと小テーブルの情報が追加（/new）
//親テーブル：user_id
//小テーブル：id、parent_id、comment、age、score

//変更の場合（/{id}）
//親テーブル：user_id
//小テーブル：id、parent_id、comment、age、score