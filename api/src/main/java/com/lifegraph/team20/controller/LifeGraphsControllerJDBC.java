package com.lifegraph.team20.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.LifeGraphServiceJDBC;
import com.lifegraph.team20.payload.request.LifeGraphdata;
@RestController
public class LifeGraphsControllerJDBC {
    @Autowired
    LifeGraphServiceJDBC service;
  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)
  public ResponseEntity<String> postController(@Valid @RequestBody LifeGraphdata data) {

      // サービスを呼ぶ
      service.resister(data);
    return ResponseEntity.ok("OK");
  }

//  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.DELETE)
//  public ResponseEntity<String> postController(@Valid @RequestBody LifeGraphdata deletedata) {
//	  service.deletedata();
//    return ResponseEntity.noContent().build();
//  }
}