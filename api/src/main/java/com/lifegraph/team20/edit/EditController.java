package com.lifegraph.team20.edit;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//目次
//①APIを作ってポストメソッドが帰ってくることを確かめたい
//②APIが通ったら用意されているデータを中に入れる（入れてる場所の設定は）
//③任意の値を取得してデータを入れる（やり方さっぱり？）
//dbAccessSampleを参考に作ってみる
//RestController定義（ボタンを押したらどうなる）
@RestController
public class EditController {
	//デバックとかの時にログを使うために仕込む
//	private static final Logger logger = LoggerFactory.getLogger(edit.class);
	//JdbcTemplateクラスのインスタンスを生成（MySQLとJdbcはつながってるJavaからとってくるにはそこが必要）
	//Bean登録したものを呼び出すことができる
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	/**
     * 登録処理サンプルメソッド
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
	Double height = 10.0;
	//(@transaction)メソッドにつくと、その作業の最初から最後で例外発生した場合はロールバックされる
    @Transactional
    //コントローラの中で利用、リクエストURLに対してメソッドが処理の実行を定義
    @RequestMapping(value = "/life-graphs", method = RequestMethod.POST)
    public Double insertSample() {
		return height;
    }
//    public static void main(String[] args) {
//    Map<String, Integer> map = new HashMap<>();
//    map.put("apple", 1);
//    map.put("orange", 2);
//    map.put("melon", 3);
//    }
//    public String insertSample(Locale locale, Model model) {
////        logger.info("Welcome insret! The client locale is {}.", locale);
//        jdbcTemplate.update("INSERT INTO prefectures (id, name, capital_city) VALUES (1000, \"ハワイ\", \"ホノルル\")");
//        model.addAttribute("resultMessage", "登録完了");
//        model.addAttribute("items", setSelectList());
//        return "dbAccessSample";
//    }
//	private Object setSelectList() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
}