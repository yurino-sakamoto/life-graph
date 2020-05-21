package com.lifegraph.team20.payload.response;

import lombok.Data;

//---------------------②インスタンス生成（４要素を紐づける）ーーーーーーーーーーーーーーー
//
@Data
public class AccountReference {
// フィールド（クラス直下の属性郡）
  private Long id;
  private String username;
  private String name;

// コンストラクタ（インスタンス化と同時にクラスに値を投げる。）
  public AccountReference(Long id, String username, String name) {

    this.id = id;
    this.username = username;
    this.name = name;// roleのname
    // this.でインスタンスに値を渡す。

  }
}
