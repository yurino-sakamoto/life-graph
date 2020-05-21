package com.lifegraph.team20.payload.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterRequest {

  @NotNull
  private Long userId;

  // when create, this is null.
  // when update, this is not null.
  private Long parentId;

  private List<RegisterChildRequest> children;
}