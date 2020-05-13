package com.lifegraph.team20.search;

import lombok.Data;

@Entity
@Data
public class Search {

	@Id
	@Column(name="goods_id")
	private String goodsId;

	@Colunm(name="goods_name")
	private String goodsName;
}



//@SpringBootApplication
//public class Search {
//	public static void main(String[] args) {
//		SpringApplication.run(Search.class, args);
//	}
//}