package com.lifegraph.team20.payload.response;

import lombok.Data;

//---------------------②インスタンス生成（４要素を紐づける）ーーーーーーーーーーーーーーー
//
@Data
public class ChildReference {
// フィールド（クラス直下の属性郡）

  private Long id;
  private Long parent_id;
  private int age;
  private int score;
  private String comment;

// コンストラクタ（インスタンス化と同時にクラスに値を投げる。）
  public ChildReference(Long id, Long parent_id, int age, int score, String comment) {

    this.id = id;
    this.parent_id = parent_id;
    this.age = age;
    this.score = score;
    this.comment = comment;
    // this.でインスタンスに値を渡す。

  }
}
