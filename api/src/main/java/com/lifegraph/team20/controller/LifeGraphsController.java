package com.lifegraph.team20.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.Service.LifeGraphService;
import com.lifegraph.team20.payload.request.RegisterRequest;
import com.lifegraph.team20.payload.response.Account;
import com.lifegraph.team20.payload.response.ChildGraph;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LifeGraphsController {

  @Autowired
  private LifeGraphService service;

  // 保存（APIで使い分ける 問題点：1テーブル）
  @PostMapping(value = "/life-graphs")
  public ResponseEntity<Void> postContent(@RequestBody @Valid RegisterRequest request) {
    service.register(request);

    // TODO URL指定をする
    return ResponseEntity.created(null).build();
  }

  // TODO 管理者権限関連の設定
  // パスパラメータを受け取れていない（/1のこと パスバリアブルでidを取得すると1が返却（調べる））
  // 削除（現状、問題点：1テーブルのみ）
  @DeleteMapping(value = "/life-graphs/{parentId}")
  public ResponseEntity<Void> deleteTable(@PathVariable("parentId") long parentId) {
    service.deleteTable(parentId);
    return ResponseEntity.noContent().build();
  }

  // //保存（APIで使い分ける 問題点：1テーブル）
  // @RequestMapping(value = "/new",method = RequestMethod.POST)
  // Parentdata postContent(@RequestBody Parentdata parentdata) {
  // return contentService.postContent(parentdata);
  // }

  // // 見るやつ（いらん）
  // @RequestMapping(value = "/a", method = RequestMethod.GET)
  // List<ChildGraph> getContent() {
  // //
  // return contentService.getContent();
  // }
  //
  // // 更新（APIで使い分ける）
  // @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  // ChildGraph putTweet(@PathVariable("id") Integer id, @RequestBody ChildGraph
  // content) {
  // content.setId(id);
  // return contentService.updateContent(content);
  // }
  // }
  //
  //// 新規登録：ボタンが押される
  //// 親テーブルと小テーブルの情報が追加（/new）
  //// 親テーブル：user_id
  //// 小テーブル：id、parent_id、comment、age、score
  //
  //// 変更の場合（/{id}）
  //// 親テーブル：user_id
  //// 小テーブル：id、parent_id、comment、age、score

  // アカウント参照API＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
  @GetMapping("/accountReference/{id}")
  public ResponseEntity<Account> responseReference(@PathVariable("id") Integer id) {

    Account accountRef = selectUserInfo(id);
    return ResponseEntity.ok(accountRef);
  }

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private Account selectUserInfo(Integer id) {
    final String sql = "select users.id, username, name from users inner join user_roles on users.id = user_roles.user_id\n"
        + "inner join roles on roles.id = user_roles.role_id where users.id = :id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    Account selectInfo = jdbcTemplate.queryForObject(sql, param, new RowMapper<Account>() {
      public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Account(rs.getLong("id"), rs.getString("username"), rs.getString("name"));// nameはroleテーブルのname（権限）
      }
    });
    return selectInfo;
  }

  // 人生グラフ参照API＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
  @RestController
  public class ReferenceController {

    @GetMapping("/reference/{userId}")
    public ResponseEntity<List<ChildGraph>> responseReference(@PathVariable("userId") Integer userId) {

      List<ChildGraph> childRef = selectChildRef(userId);
      return ResponseEntity.ok(childRef);
    }

    private List<ChildGraph> selectChildRef(Integer userId) {
      final String sql = "select * from child_graphs where parent_id = (select id from parent_graphs where user_id = :userId);";
      SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
      List<ChildGraph> selectChildInfo = jdbcTemplate.query(sql, param, new RowMapper<ChildGraph>() {
        public ChildGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
          return new ChildGraph(rs.getLong("id"), rs.getLong("parent_id"), rs.getInt("age"), rs.getInt("score"),
              rs.getString("comment"));
        }
      });
      return selectChildInfo;
    }
  }
}