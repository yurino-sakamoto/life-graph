package com.lifegraph.team20.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

//Controller定義（ボタンを押したらどうなる）
@Controller
public class edit {

	//JdbcTemplateを定義（MySQLとJdbcはつながってるJavaからとってくるにはそこが必要）
	@Autowired
	private JdbcTemplate jdbcTemplate;

}