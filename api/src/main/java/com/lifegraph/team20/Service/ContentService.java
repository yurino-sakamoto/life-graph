package com.lifegraph.team20.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.models.Child_graphs;
import com.lifegraph.team20.models.Parent_graphs;
import com.lifegraph.team20.payload.request.ResisterRequest;
import com.lifegraph.team20.repository.ContentRepository;
import com.lifegraph.team20.repository.ParentRepository;

@Service
@Transactional
public class ContentService {
	@Autowired
	ContentRepository contentRepository;
	@Autowired
	ParentRepository parentRepository;

	//親idを持たせておく
	//①親idを追加or更新
	//②子供idを追加更新(idは自動挿入、parent_idで更新か)

	//保存（APIで使い分ける　問題点：1テーブル）
	//できること
	//年齢のみの変更→○（小テーブルidが必要）
	//コメントのみの変更→○
	//スコアのみの変更→○
	//新規追加→可能

	//できないこと親テーブルがないのに追加
	public Child_graphs postContent(Child_graphs child_graphs) {
		return contentRepository.save(child_graphs);
	}

	public Parent_graphs postContent(ResisterRequest request) {
//		LocalDateTime nowDateTime = LocalDateTime.now();
//		parent_graphs.setCreatedAt(nowDateTime);
//		parent_graphs.setUpdatedAt(nowDateTime);
		if(request.getParentId() == null) {

			parentRepository.save();

		}else {


		}
		Optional<Parent_graphs> parent = parentRepository.findById(request.getParentId());
		return parentRepository.save(request);
	}

	public List<Child_graphs> getContent() {
		return contentRepository.findAll();
	}

	//更新（APIで使い分ける）
	public Child_graphs updateContent(Child_graphs content) {
		return contentRepository.save(content);
	}

	//削除（現状、問題点：1テーブルのみ）
	public void deleteContent(Integer id) {
		contentRepository.deleteById(id);
	}
}