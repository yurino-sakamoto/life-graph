package com.lifegraph.team20.payload.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterChildRequest {

  @NotNull
  private Integer age;

  @NotNull
  private Integer score;

  private String comment;
}