//package com.lifegraph.team20.models;
//
//import javax.persistence.Entity;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Size;
//
//import com.sun.istack.NotNull;
//
//import lombok.Data;
//
//@Data
//@Entity
//public class LifeGraphdata {
//
////  @Pattern(regexp = "[0-9,-]+") //intで受け取れるならこの文いらないかな？
//    @NotNull
//    @Max(255)
//    @Min(0)
//    private int age;
//
//    @NotNull
//    @Max(100)
//    @Min(-100)
//    private int score;
//
//    @NotNull
//    @Size(max = 255, min = 0)
//    private String comment;
//
//    private int Id;
//}