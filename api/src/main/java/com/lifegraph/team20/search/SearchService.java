//<jap>
//
//package com.lifegraph.team20.search;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class SearchService {
//
//	@Autowired
//	SearchRepository searchrepository;
//
//	public List<Search> getSearch() {
//		return searchrepository.findAll();
//	}
//
//	@Autowired
//	ParentGraphRepository repository;
//
//	public List<parent_graph> getGraph() {
//		return repository.findAll();
//	}
//}