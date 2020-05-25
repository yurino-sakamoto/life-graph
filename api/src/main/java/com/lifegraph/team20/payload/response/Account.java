package com.lifegraph.team20.payload.response;

import lombok.Data;

@Data
public class Account {

  private Long id;
  private String username;
  private String name;

  public Account(Long id, String username, String name) {

    this.id = id;
    this.username = username;
    this.name = name;
  }
}
