package com.lifegraph.team20.Delete;

import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteSample {

//	    private static final Logger logger = LoggerFactory.getLogger(dbAccessSampleController.class);
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @Transactional
	    @DeleteMapping("/deleteSample")
	    public String deleteSample(Locale locale, Model model) {
//	        logger.info("Welcome delete! The client locale is {}.", locale);
	        jdbcTemplate.update("DELETE FROM prefectures WHERE id = 1000");
	        model.addAttribute("resultMessage", "削除完了");
	        model.addAttribute("items", setSelectList());
	        return "dbAccessSample";
	    }

		private Object setSelectList() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}
}
