//package com.lifegraph.team20.Reference;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class TestController2 {
//  @Autowired
//  TestRepositry2 empRepository;
//
//  @RequestMapping(value = "/Aaa", method = RequestMethod.GET)
//  public String index(Model model) {
//    List<Test> emplist = empRepository.findAll();
//    model.addAttribute("emplist", emplist);
//    return "index";
//  }
//}