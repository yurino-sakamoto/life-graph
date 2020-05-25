package com.lifegraph.team20.payload.response;

import lombok.Data;

@Data
public class ChildGraphReference {

  private Long id;
  private Long parent_id;
  private int age;
  private int score;
  private String comment;

  public ChildGraphReference(Long id, Long parent_id, int age, int score, String comment) {

    this.id = id;
    this.parent_id = parent_id;
    this.age = age;
    this.score = score;
    this.comment = comment;

  }
}
