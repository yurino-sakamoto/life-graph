package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.ChildGraph;

public interface ChildGraphRepository extends JpaRepository<ChildGraph, Long> {

  long deleteByParentId(Long parentId);
}