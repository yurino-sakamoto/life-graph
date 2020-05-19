package com.lifegraph.team20.edit.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.edit.Deletedata;
import com.lifegraph.team20.edit.service.ExsistService;

@RestController
public class LifeGraphsController {
//	@Autowired
//    InsertService GraphService;
//	Validation Varidation;

	//生じてる問題→ResponseTransferのエラーが出る
	//@Responsebody:戻り値がそのままレスポンスのコンテンツになる（JSONやXMLなどを返す場合に使用される）Controllerに付随している
	//Springは、適切なJSONが指定されていると想定し、JSONを自動的にJava型に変換する。
	@PostMapping("/life-graphs")
	public ResponseEntity<HttpStatus> postController(@Valid @RequestBody LifeGraphdata responseTransfer) {
		LifeGraphdata lifegraph = responseTransfer;
		System.out.println(lifegraph);

		//存在チェック//何でだめ
		ExsistService.exist(lifegraph);

		//レスポンスを返す
	    return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/lifegraphs{userId}")
	public ResponseEntity<HttpStatus> postController(@Valid @RequestBody Deletedata responseTransfer) {
		LifeGraphdata lifegraph = responseTransfer;
		System.out.println(lifegraph);

		//削除用のメソッドを流す
//		delete();

		//レスポンスを返す
		return ResponseEntity.noContent().build();
	}
}
