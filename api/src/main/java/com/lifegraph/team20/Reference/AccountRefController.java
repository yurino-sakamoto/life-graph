package com.lifegraph.team20.Reference;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//-----------------------------Controller定義----------------------------
@RestController
public class AccountRefController {

//-----------------------------vueからのリクエストに対する処理----------------------------
  @RequestMapping(value = "/accountReference/{id}", method = RequestMethod.GET) // vue側のgetメソッドと合わせる・・？
  // リクエストに応じたメソッド「responseReference」を定義。
  // レスポンスで{id:ーー, username:ーー, name:ーー}という形のデータを返したい。
  // 「RequestMappingの{id}」が「@PathVariable("id") Integer id のid」になる。{id}はログイン情報。
  public ResponseEntity<AccountReference> responseReference(@PathVariable("id") Integer id) {

    // メソッド① ： 3つのテーブルからデータを取得・整理（ログイン担当のテーブル構成）
    AccountReference accountRef = selectUserInfo(id);

    return ResponseEntity.ok(accountRef);
    // {id:1, username:yu3, name:ROLE_USER}
  }

//-----------------------------【SOLからデータを取得】----------------------------

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate; // paramを使えるjdbcTemplate

  private AccountReference selectUserInfo(Integer id) {
    final String sql = "select users.id, username, name from users inner join user_roles on users.id = user_roles.user_id\n"
        + "inner join roles on roles.id = user_roles.role_id where users.id = :id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    // paramでSQL文の文字列"id"を引数idにする。
    AccountReference selectInfo = jdbcTemplate.queryForObject(sql, param, new RowMapper<AccountReference>() {
      // 「queryForObject」1つのオブジェクト（ここではレコード）のみ取得。「(sql, param,」でSQL文が完成。
      public AccountReference mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AccountReference(rs.getInt("id"), rs.getString("username"), rs.getString("name"));// nameは権限
      }
    });
    return selectInfo;
  }
}
//【SELECT文】
//select users.id, username, name from users    「users」テーブルから「users.id, username, name」選択
//inner join user_roles                          結合させるテーブルを指定「user_roles」
//on users.id = user_roles.user_id\n"            どこで結合させるかを指定。userの中のidと・・
// + "inner join roles on roles.id = user_roles.role_id     セレクト文をさらに追加。"~\n" + "~"
//where users.id = :id;"                        「:id」で引数を入れれる。usersの中のidが引数のidと一致するレコードを取得。
//※user_rolesは完全な仲介役
