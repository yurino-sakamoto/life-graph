//package com.lifegraph.team20.Reference;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Controller;
////-----------------------------Controller定義----------------------------
//@Controller
//public class ReferenceController {
//  public static void main(String[] args) {
//// ロガー出力
//// public static final Logger logger =
//// LoggerFactory.getLogger(ReferenceController.class);
//// public String skillUpload(Locale locale, Model model) throws IOException {
//// logger.info("Welcome update! The client locale is {}.", locale);
////-----------------------------mainメソッド----------------------------
//    /*
//     * ①で定義された「selectReference」メソッドの起動。 取得した値をList型変数「select」に代入。
//     * 「select」の内容をコンソールに表示。
//     */
//    List<Reference> childRef = selectReference();
//    System.out.println(childRef);
//    List<Reference> parentRef =
//  }
////配列ではない　テーブルごと持っていてる　
////-----------------------------①SOLからデータを取得----------------------------
////jdbcTemplateを使えるようにしている。MySQLと接続の設定を補助してくれる。ライブラリ参照。
//  @Autowired
//  public static JdbcTemplate jdbcTemplate;
//  /*
//   * (1)List型を返すメソッド「selectReference」を定義
//   * (2)String型の変数「sql」を定義。「sample」テーブルから全てのデータを取得。
//   * (3)jdbcTemplateクラスの「queryメソッド」を起動。（今回jdbcTemplateライブラリ入ってないかも？）
//   * (4)mapRowメソッド。 (5)Referenceクラスを参照し、3つの引数から「？？？？」という名前のインスタンスを生成。
//   * rs(ResultSet)クラスのgetStringメソッドでsampleIDなどを指定し、Referenceクラスに渡す。 →
//   * Referenceクラスからインスタンが返却される。
//   *
//   * SQLのテーブルからバラバラに取得し、その値からReferenceクラスを参照してインスタンスを生成する（４要素を紐づける）指令をだした。
//   * ②に移行し、インスタンスを生成する。
//   */
//  public static List<Reference> selectReference() {
//    final String sql = "select * from child_graphs";
//    return jdbcTemplate.query(sql, new RowMapper<Reference>() {
//      public Reference mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new Reference(rs.getInt("id"), rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
//      }
//    });
//  }
//// ----------------------------③①で生成したインスタンスからデータを整形----------------------------
//  /*
//   * (1)「uploadReferenceメソッド」を定義。List型と (1)Map<String,
//   * Object>型のList変数「dataList」を定義。 (2)Map<String, Object>型の変数「map」を定義。（未代入）
//   * (3)skills.stream();
//   * (4)list.stream()メソッドで、変数listをストリームに渡す。collect()メソッドからCollectors.groupingBy()
//   * メソッド（map内の要素ごとにグループをつくる）を渡す (5)拡張for文。3回回している。（バックエンドのキーに3つのバリュー）
//   * entrySetメソッド：Mapのコレクションを返す（Mapクラスのメソッド） (6)mapの初期化 (7)map.put(key,value);
//   * put()メソッド：キーと値をHashMapに返却。 (8)「entry.getValue」でvalueを返却。(どこからどこへ？)
//   * (9)add()メソッドでmapの値をdataListへ
//   *
//   * ①で生成したインスタンスを取得し、 //
//   */
//  public void uploadReference(List<Reference> childRef) {
//    List<Map<String, Object>> dataList = new ArrayList<>();
//    Map<String, Object> map;
//    childRef.stream();
//    Map<String, List<Reference>> referenceMap = childRef.stream().collect(Collectors.groupingBy(Reference::getId));
//    for (Map.Entry<String, List<Reference>> entry : referenceMap.entrySet())// ①〜④を繰り返す
//      map = new HashMap<String, Object>(); // ①初期化して
//    map.put("sampleComment", entry.getKey()); // ②キーを取得
//    map.put("sampleAge", entry.getValue()); // ③バリューを取得
//    dataList.add(map); // ④返却
//  }
//}
////
///*
// * 【謎】 ・データが来ていることを確認する方法（
// * System.out.println(map.get(rs.getString("sampleUserID")))とか？ ） ・①で値はどんな状態？
// * ・②で値はどんな状態？ ・③で値はどんな状態？ ・どこがゴール？ ・
// *
// */