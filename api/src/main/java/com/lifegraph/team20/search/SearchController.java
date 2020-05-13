package com.lifegraph.team20.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//サーチ情報 Controller
@Controller
public class SearchController {



@Autowired

private JdbcTemplate jdbcTemplate;

public List<SearchCategory> selectSearchCategories() {
	final String sql = "select * from parent_graphs";
	return jdbcTemplate.query(sql, new RowMapper<SearchCategory>() {
		public SearchCategory mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new se
		}
	}
}

	@GetMapping(value = "SearchList")
	public String displayList(Model model) {
		List<Search>
	}

//	@RequestMapping("/zipcode")
//	public String zipcodeForm(HttpSession session, Model nodel) {
//		return "zipcode";
//	}
//
//	@RequestMapping(value="/")
}
