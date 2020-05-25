package com.lifegraph.team20.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterChildRequest {

  @NotNull
  @Max(120)
  @Min(0)
  private Integer age;

  @NotNull
  @Max(100)
  @Min(-100)
  private Integer score;

  private String comment;
}
