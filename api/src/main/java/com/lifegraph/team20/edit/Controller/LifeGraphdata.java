package com.lifegraph.team20.edit.Controller;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


//HTTPリクエストを受け取るためのクラスここに情報の定義が集まっている
@Data
@Getter
@Setter
public class LifeGraphdata {
//StringじゃなくてもOK
	@NotNull
	private Integer age;

	private String comment;

	@NotNull
	private Integer score;
}
