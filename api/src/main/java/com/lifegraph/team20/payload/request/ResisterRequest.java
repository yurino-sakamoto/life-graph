package com.lifegraph.team20.payload.request;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ResisterRequest {
	//Integerはobject（参照型）

    @Id
//    オートインクリーメント的なもの
//    @GeneratedValue
    private Long userId;

//    @Column(nullable = false)
    private Long parentId;

//    @Column(nullable = false)
    private Integer age;

//    @Column(nullable = false)
    private Integer score;

//    @Column(nullable = ture)
    private String comment;

}