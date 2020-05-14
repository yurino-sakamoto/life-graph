//
//package com.lifegraph.team20;
//
//import org.springframework.boot.SpringApplication;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
////Controller定義
//@Controller
//public class ReferenceController {
//
////ロガー出力
//  public static final Logger logger = LoggerFactory.getLogger(ReferenceController.class);
//  public String skillUpload(Locale locale, Model model) throws IOException {
//    logger.info("Welcome update! The client locale is {}.", locale);
//
////---------------------------「selectReference」メソッドの起動。取得した値をList型変数「select」に代入。
//    List<Reference> select = selectReference();
//  }
//
////-----------------------------MySOLからデータの取得----------------------------
//
////jdbcTemplateを使えるようにしている。MySQLと接続の設定を補助してくれる。ライブラリ参照。
//  @Autowired
//  public JdbcTemplate jdbcTemplate;
//
//  /*
//   * (1)List型を返すメソッド「selectReference」を定義
//   * (2)String型の変数「sql」を定義。「sample」テーブルから全てのデータを取得。
//   * (3)jdbcTemplateライブラリで・・・・？
//   * (4)
//   * (5)「Reference」の値を初期化・返却する。rs(ResultSet)クラスのgetStringでsampleIDなどを指定している。
//   */
//
//  public List<Reference> selectReference() {
//    final String sql = "select * from sample";
//    return jdbcTemplate.query(sql, new RowMapper<Reference>() {
//      public Reference mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new Reference(rs.getString("sampleID"), rs.getString("sampleComment"), rs.getInt("sampleAge"));
//      }
//    });
//  }
//
//
//// ----------------------------データの取得----------------------------
///*
// * (1)List型の変数「dataList」を定義。返す値はMap<String, Object>型。 (2)Map<String,
// * Object>方の変数「map」を定義。（未代入） (3)skills.stream();
// * (4)拡張for文。バックエンドのキーに3つのバリュー。を3回回している。 (5)mapの初期化(型を指定していない) (6)map.put(key,
// * value); putメソッドはキーと値をHashMapに返却。左はString型なので文字列。
// * (7)「entry.getValue」でvalueを返却。
// */
//
//    List<Map<String, Object>> dataList = new ArrayList<>();
//    Map<String, Object> map;
//    skills.stream();
//    Map<String, List<Skill>> skillMap = skills.stream().collect(Collectors.groupingBy(Skill::getCategory));
//    for (Map.Entry<String, List<Skill>> entry : skillMap.entrySet()) {
//      map = new HashMap<String, Object>();
//      map.put("category", entry.getKey());
//      map.put("skills", entry.getValue());
//
//      dataList.add(map);
//    }
//
