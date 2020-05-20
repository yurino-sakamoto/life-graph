//package com.lifegraph.team20.Service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lifegraph.team20.payload.request.LifeGraphData;
//import com.lifegraph.team20.payload.request.LifeGraphdata;
//import com.lifegraph.team20.repository.ChildLifeGraphRepository;
//import com.lifegraph.team20.repository.ParentLifeGraphRepository;
//
//@Service
//public class LifeGraphServiceJDBC {
//  @Autowired
//  private ParentLifeGraphRepository parentRepository;
//  @Autowired
//  private ChildLifeGraphRepository childRepository;
//
//  /**
//   * 登録・編集のメイン処理
//   *
//   * @param data 登録・編集リクエスト
//   */
//  public void resister(LifeGraphData data) {
//    // parent table
//    long parentId = resisterParent(data.getUserId());
//    // child table
//    registerChild(parentId, data);
//  }
//
//  /**
//   * 親グラフの登録・更新を行う
//   *
//   * @param userId ユーザーID
//   * @return 親グラフID
//   */
//  private long resisterParent(long userId) {
//    // ユーザーIDを元に親テーブルがあるか確認する
//    long parentId;
//    Optional<LifeGraphdata> parent = parentRepository.findByUserId(userId);
//    // 親IDが存在する場合
//    if (parent.isPresent()) {
//      // 更新日時をアップデートする
//      parentId = parent.get().getUserId();
//    } else {
//      // 親IDが存在しない場合→親のレコード挿入
//      parentId = parentRepository.insert(userId);
//    }
//    return parentId;
//  }
//
//  private void registerChild(long parentId, LifeGraphdata data) {
//    // exists parent by userId
//    // 子供のIDがあれば、更新する
//    if (childRepository.existsByUserIdAndAge(parentId, data.getAge())) {
//      // 受け取った parentIdとageを確認するためにselect文を使って確認する
//      // update
//      childRepository.updateChild(parentId, data.getAge(), data.getScore(), data.getComment());
//      // 更新した時にすでにageが存在する場合、エラーを出す
//      // エラーステータス409が出るかも？？
//      // でプリケイどエラーを調べる
//    } else {
//      // insert
//      // 更新した時にすでにageが存在する場合、エラーを出す
//      childRepository.addChild(parentId, data.getAge(), data.getScore(), data.getComment());
//    }
//  }
//
////  public void deletedata() {
////	  if(管理者権限があれば) {
////		  消す
////	  }else {
////		  消さない
////	  }
//}
