//package com.lifegraph.team20.edit.models;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import lombok.Data;
//
////@Data(getter,setterの省略（lombok）),@Entity(エンティティの作成→repositoryに付随する情報を入れる部分),
//@Data
//@Entity
//@Table(name = "child_lifegraph")
//public class ChildLifegraph {
//	//@IDプライマリキーに付ける,@GeneratedValueはIdで指定したプライマリキーの採番方法を指定する。
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
//	//カラムをくっつける時の小テーブルにつける
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//	private long parent_id;
//
//	//UNIQUE指定
//	@NotBlank
//	private int age;
//
//	@NotBlank
//	private int score;
//	private long comment;
//
//	public ChildLifegraph(long id, long parent_id, int age,int score,long comment) {
//		this.id = id;
//		this.parent_id = parent_id;
//		this.age = age;
//		this.score = score;
//		this.comment = comment;
//	}
//}
//
////参考
////	@Data
////	@Entity
////	@Table(name = "parents_lifegraph")
////	public class Role {
////		@Id
////		@GeneratedValue(strategy = GenerationType.IDENTITY)
////		private Integer id;
////
////		@Enumerated(EnumType.STRING)
////		@Column(length = 20)
////		private ERole name;
////
////		public Role() {
////
////		}
////
////		public setRole(ERole name) {
////			this.name = name;
////		}
////
////	}
