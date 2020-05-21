package com.lifegraph.team20.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

import com.lifegraph.team20.payload.response.ChildReference;

@RestController
public class ReferenceController {

//-----------------------------vueからのリクエストに対する処理----------------------------
  @RequestMapping(value = "/reference/{userId}", method = RequestMethod.GET)
  public ResponseEntity<List<ChildReference>> responseReference(@PathVariable("userId") Integer userId) {

    List<ChildReference> childRef = selectChildRef(userId);
    return ResponseEntity.ok(childRef);
  }

//-----------------------------【SOLからデータを取得】----------------------------

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private List<ChildReference> selectChildRef(Integer userId) {
    final String sql = "select * from child_graphs where parent_id = (select id from parent_graphs where user_id = :userId);";
    SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
    List<ChildReference> selectChildInfo = jdbcTemplate.query(sql, param, new RowMapper<ChildReference>() {
      public ChildReference mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChildReference(rs.getLong("id"), rs.getLong("parent_id"), rs.getInt("age"), rs.getInt("score"),
            rs.getString("comment"));
      }
    });
    return selectChildInfo;
  }
}