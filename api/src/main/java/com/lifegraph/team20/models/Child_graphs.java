package com.lifegraph.team20.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lifegraph.team20.payload.request.ResisterRequest;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "child_graphs")
public class Child_graphs {

	@Id
    private Integer id;

    @Column( name = "parent_id")
    private Long parentId;

    @NotNull
    @Max(99)
    @Min(1)
    private Integer age;

    @Max(100)
    @Min(-100)
    private Integer score;

    @Size(max = 100, min = 1)
    private String comment;

    public Child_graphs (ResisterRequest resisterReques){
    	this.parentId=resisterReques.getParentId();
    	this.age=resisterReques.getAge();
    	this.comment=resisterReques.getComment();
    	this.score=resisterReques.getScore();
    }
}