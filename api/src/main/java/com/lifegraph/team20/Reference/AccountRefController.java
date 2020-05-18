//package com.lifegraph.team20.Reference;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
////-----------------------------Controller定義----------------------------
//@RestController
//public class AccountRefController {
//
////-----------------------------vueからのリクエストに対する処理----------------------------
//  @RequestMapping(value = "/accountReference", method = RequestMethod.GET) // vue側のgetメソッドと合わせる・・？
//  public List<Map<String, Object>> responseReference() { // リクエストに応じたメソッド「responseReference」を定義。
//    // メソッド① ： child_graphsからデータを取得・整理
//    List<AccountReference> accountRef = selectUserInfo();
//
//    // メソッド② ： ①で取得したデータをvueのリクエストに合うように成形
//    List<Map<String, Object>> arrangeAccountRef = uploadAccountRef(accountRef);
//
//    return arrangeAccountRef;
//  }
//
////-----------------------------【SOLからデータを取得】----------------------------
//
//  @Autowired
//  private JdbcTemplate jdbcTemplate;
//
//  private List<AccountReference> selectUserInfo() {
//    final String sql = "select id,authority,name from users";
//    return jdbcTemplate.query(sql, new RowMapper<AccountReference>() {
//      public AccountReference mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new AccountReference(rs.getInt("id"), rs.getInt("authority"), rs.getString("name"));
//      }
//    });
//  }
//
//// ----------------------------【取得したデータを整形】----------------------------
//
//  private List<Map<String, Object>> uploadAccountRef(List<AccountReference> accountRef) {
//    List<Map<String, Object>> dataList = new ArrayList<>();
//    Map<String, Object> map;
//    for (AccountReference userInfo : accountRef) {
//      map = new HashMap<>();
//      map.put("id", userInfo.getId());
//      map.put("authority", userInfo.getAuthority());
//      map.put("name", userInfo.getName());
//      dataList.add(map);
//    }
//    return dataList;
//  }
//}
