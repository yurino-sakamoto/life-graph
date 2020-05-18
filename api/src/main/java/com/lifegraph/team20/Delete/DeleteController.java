package com.lifegraph.team20.Delete;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lifegraph.team20.edit.Controller.LifeGraphdata;

public class DeleteController {
	@DeleteMapping("/life-graphs")
	public ResponseEntity<HttpStatus> postController(@Valid @RequestBody LifeGraphdata responseTransfer) {
		LifeGraphdata lifegraph = responseTransfer;
		System.out.println(lifegraph);

		//存在チェック
//		exist(num);

		//レスポンスを返す
	    return ResponseEntity.ok(HttpStatus.OK);
	}
}
