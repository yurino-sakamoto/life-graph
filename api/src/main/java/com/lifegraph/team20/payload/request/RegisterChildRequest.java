package com.lifegraph.team20.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterChildRequest {

  @NotNull
  @Max(100)
  @Min(0)
  private Integer age;

  @NotNull
  @Max(100)
  @Min(-100)
  private Integer score;

  @Size(max = 100, min = 1)
  private String comment;
}
