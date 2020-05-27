package com.lifegraph.team20.payload.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterRequest {

  @NotNull
  private Long userId;

  private Long parentId;

  private List<RegisterChildRequest> children;
}