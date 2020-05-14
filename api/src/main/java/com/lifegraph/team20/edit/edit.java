//package com.lifegraph.team20.edit;
//
//import java.util.Locale;
//import java.util.logging.Logger;
//
//import org.hibernate.validator.internal.util.logging.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
////dbAccessSampleを参考に作ってみる
////Controller定義（ボタンを押したらどうなる）
//@Controller
//public class edit {
//	//デバックとかの時にログを使うために仕込む
//	private static final Logger logger = LoggerFactory.getLogger(edit.class);
//	//JdbcTemplateクラスのインスタンスを生成（MySQLとJdbcはつながってるJavaからとってくるにはそこが必要）
//	//Bean登録したものを呼び出すことができる
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	/**
//     * 登録処理サンプルメソッド
//     *
//     * @param locale ロケール情報
//     * @param model モデル情報
//     * @return 遷移先画面名
//     */
//	//(@transaction)メソッドにつくと、その作業の最初から最後で例外発生した場合はロールバックされる
//    @Transactional
//    //コントローラの中で利用、リクエストURLに対してメソッドが処理の実行を定義
//    @RequestMapping(value = "/life-graphs", method = RequestMethod.POST)
//    //
//    public String insertSample(Locale locale, Model model) {
//        logger.info("Welcome insret! The client locale is {}.", locale);
//        jdbcTemplate.update("INSERT INTO prefectures (id, name, capital_city) VALUES (1000, \"ハワイ\", \"ホノルル\")");
//        model.addAttribute("resultMessage", "登録完了");
//        model.addAttribute("items", setSelectList());
//        return "dbAccessSample";
//    }
//}
