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

	//親テーブルのおはなし
	private void registerParent(RegisterRequest request) {
		//Entityを初期化↓（後の方で中身の詰めかたを変えるから）
		ParentGraph parent;
		LocalDateTime now = LocalDateTime.now();

		//親テーブルがすでにある時
		if (request.getParentId() != null) {
			//ParentのレポジトリからID検索をして、parentにする（entityを書き換える）それ以外はエラー文
			// 更新の場合
			parent = parentRepository.findById(request.getParentId())
					.orElseThrow(() -> new IllegalStateException("record not found. parent id : " + request.getParentId()));
		} else {
			//インスタンス生成して、中に詰める
			// create
			parent = new ParentGraph(request);
			parent.setCreatedAt(now);
		}
		// 新規登録の場合
		//時間を詰める
		parent.setUpdatedAt(now);
		//try catchで例外処理を返す
		try {
			//普通パターンparentを詰める
			parent = parentRepository.save(parent);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalStateException("already exits parent graph. user id : " + request.getUserId(), e);
			}

		//小テーブルで使うためにrequestにpatrntのidを詰める
		// set parentId to request
		request.setParentId(parent.getId());
	}


	//小テーブルのおはなし
	private void registerChildren(RegisterRequest request) {
		//parent_idが一致するものを全部消す
		//ちょっと謎深い
		// delete all by parent id
		childRepository.deleteByParentId(request.getParentId());

		//List<Children>をchildrenEntitiesという変数名にし、Childrenの情報を持ってきて、
		//ストリームとマップを使って形を変換parentIDと子供の情報という1:1の関係に変換！！
		//でそれを集めてきてList形式にする
		// convert request to entities
		List<ChildGraph> childrenEntities = request.getChildren().stream().map(child -> {
			return new ChildGraph(request.getParentId(), child);
		}).collect(Collectors.toList());

		//それで集めてきたものを全部保存する（Entity形式）
		childRepository.saveAll(childrenEntities);
	}

	//削除APIの作成
		public void deleteTable(long parentId) {

//			if(deleteRequest.getName() == "ROLE_USER") {
//				//消さない
//				//エラーを返す
//				//405
	//
//			}else {
				//消す
				childRepository.deleteByParentId(parentId);
//				//ここあってんの？？↓型を無理やり変換してるけど
				parentRepository.deleteById(parentId);
//			}
		}

	//メモ↓↓
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