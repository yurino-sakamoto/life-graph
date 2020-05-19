package com.lifegraph.team20.edit.Controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;


//HTTPリクエストを受け取るためのクラスここに情報の定義が集まっている
//@Data
//@Getter
//@Setter
//public class LifeGraphdata {
////StringじゃなくてもOK
//	@NotNull
//	private Integer age;
//
//	private String comment;
//
//	@NotNull
//	private Integer score;
//}

@Data
public class LifeGraphdata {
//  @Pattern(regexp = "[0-9,-]+") //intで受け取れるならこの文いらないかな？
    @NotNull
    @Max(120)
    @Min(0)
    private int age;

    @NotNull
    @Max(100)
    @Min(-100)
    private int score;

    @NotNull
    @Size(max = 255, min = 0)
    private String comment;

    private String name;

    private int id;
}