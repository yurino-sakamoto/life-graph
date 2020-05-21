package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.ChildGraph;

public interface ChildGraphRepository extends JpaRepository<ChildGraph, Long> {

	//別で指定することによって、(Entityジャなくてもいける説？もしくは型が一致してるから？);
	//
  long deleteByParentId(Long parentId);
}