package com.lifegraph.team20.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.models.ChildGraph;
import com.lifegraph.team20.models.ParentGraph;
import com.lifegraph.team20.payload.request.RegisterRequest;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;

@Service
@Transactional
public class LifeGraphService {

  @Autowired
  private ChildGraphRepository childRepository;
  @Autowired
  private ParentGraphRepository parentRepository;

  // 親idを持たせておく
  // ①親idを追加or更新
  // ②子供idを追加更新(idは自動挿入、parent_idで更新か)

  // 保存（APIで使い分ける 問題点：1テーブル）
  // できること
  // 年齢のみの変更→○（小テーブルidが必要）
  // コメントのみの変更→○
  // スコアのみの変更→○
  // 新規追加→可能

  public void register(RegisterRequest request) {
    // parent
    registerParent(request);
    // children
    registerChildren(request);
  }

  private void registerParent(RegisterRequest request) {

    ParentGraph parent;
    LocalDateTime now = LocalDateTime.now();
    if (request.getParentId() != null) {
      // update
      parent = parentRepository.findById(request.getParentId())
          .orElseThrow(() -> new IllegalStateException("record not found. parent id : " + request.getParentId()));
    } else {
      // create
      parent = new ParentGraph(request);
      parent.setCreatedAt(now);
    }
    // execute
    parent.setUpdatedAt(now);
    try {
      parent = parentRepository.save(parent);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException("already exits parent graph. user id : " + request.getUserId(), e);
    }
    // set parentId to request
    request.setParentId(parent.getId());
  }

  private void registerChildren(RegisterRequest request) {
    // delete all by parent id
    childRepository.deleteByParentId(request.getParentId());
    // convert request to entities
    List<ChildGraph> childrenEntities = request.getChildren().stream().map(child -> {
      return new ChildGraph(request.getParentId(), child);
    }).collect(Collectors.toList());
    childRepository.saveAll(childrenEntities);
  }

//  // できないこと親テーブルがないのに追加
//  public ChildGraph postContent(ChildGraph child_graphs) {
//    return contentRepository.save(child_graphs);
//  }
//
//  public ParentGraph postContent(RegisterRequest request) {
////		LocalDateTime nowDateTime = LocalDateTime.now();
////		parent_graphs.setCreatedAt(nowDateTime);
////		parent_graphs.setUpdatedAt(nowDateTime);
//    if (request.getParentId() == null) {
//
////			parentRepository.save();
//
//    } else {
//
//    }
//    Optional<ParentGraph> parent = parentRepository.findById(request.getParentId());
////		return parentRepository.save(request);
//    return null;
//  }
//
//  public List<ChildGraph> getContent() {
//    return contentRepository.findAll();
//  }
//
//  // 更新（APIで使い分ける）
//  public ChildGraph updateContent(ChildGraph content) {
//    return contentRepository.save(content);
//  }
//
//  // 削除（現状、問題点：1テーブルのみ）
//  public void deleteContent(Integer id) {
//    contentRepository.deleteById(id);
//  }
}