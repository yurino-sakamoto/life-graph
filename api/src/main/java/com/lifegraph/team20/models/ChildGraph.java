package com.lifegraph.team20.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.lifegraph.team20.payload.request.RegisterChildRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "child_graphs")
public class ChildGraph {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "parent_id")
  private Long parentId;

  @NotNull
  @Max(99)
  @Min(1)
  private Integer age;

  @Max(100)
  @Min(-100)
  private Integer score;

  @Max(255)
  private String comment;

  public ChildGraph(Long parentId, RegisterChildRequest request) {
    this.parentId = parentId;
    this.age = request.getAge();
    this.comment = request.getComment();
    this.score = request.getScore();
  }
}