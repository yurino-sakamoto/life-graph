package com.lifegraph.team20.Reference;

//---------------------②インスタンス生成（４要素を紐づける）ーーーーーーーーーーーーーーー
//
public class AccountReference {
// フィールド（クラス直下の属性郡）
  private int id;
  private String username;
  private String name;

// コンストラクタ（インスタンス化と同時にクラスに値を投げる。）
  public AccountReference(int id, String username, String name) {

    this.id = id;
    this.username = username;
    this.name = name;// roleのname
    // this.でインスタンスに値を渡す。

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setAuthority(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
