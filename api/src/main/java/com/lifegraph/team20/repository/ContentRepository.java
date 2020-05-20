package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.Child_graphs;

public interface ContentRepository extends JpaRepository<Child_graphs, Integer> {}