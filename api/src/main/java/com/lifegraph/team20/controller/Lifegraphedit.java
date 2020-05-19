//package com.lifegraph.team20.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lifegraph.team20.Service.editService;
//import com.lifegraph.team20.models.LifeGraphdata;
//
//
//@RestController
//public class Lifegraphedit {
//	@RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)
//	public ResponseEntity<String> postController(@Valid @RequestBody LifeGraphdata data) {
//		//    LifeGraphData lifegraph = data;
//		//    LifeGraphData lifegraph = data;
//
//		long id = data.getId();
//		int ageInt = data.getAge();
//		int scoreInt = data.getScore();
//		String comment = data.getComment();
//
//		//存在チェック
//		editService.exists(id, ageInt);
//
//		//    return ResponseEntity.ok("OK");
//		return ResponseEntity.ok("OK");
//	}
//}
