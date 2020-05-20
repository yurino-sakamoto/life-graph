
package com.lifegraph.team20.Reference;

//---------------------②インスタンス生成（５要素を紐づける）ーーーーーーーーーーーーーーー
//
public class ParentReference {
// フィールド（クラス直下の属性郡）
  private int id;

// コンストラクタ（インスタンス化と同時にクラスに値を投げる。）
  public ParentReference(int id) {

    this.id = id;

    // this.でインスタンスに値を渡す。

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}