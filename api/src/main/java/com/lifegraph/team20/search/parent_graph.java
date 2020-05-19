//①
//package com.lifegraph.team20.search;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name="Parent_graphs")
//public class parent_graph {
//
//	@Id
//	@Column(name="user_id")
//	@Getter
//	@Setter
//	private int userId;
//
//	@Column(name="created_at")
//	@Getter
//	@Setter
//	private String createdAt;
//
//	@Column(name="updated_at")
//	@Getter
//	@Setter
//	private String updatedAt;

//③
//package com.lifegraph.team20.search;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Data
//@Entity
//@Table(name="users")
//public class parent_graph {
//
//	@Id
//	@Column(name="id")
//	@Getter
//	@Setter
//	private long id;
//
//	@Column(name="name")
//	@Getter
//	@Setter
//	private String Name;
//
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "id", referencedColumnName = "user_id", insertable=false, updatable=false)
//	private Search parent_graphs;
//}

//②
//@Data
//@Entity
//@Table (name = "parent_graphs",
//	uniqueConstraints = {
//		@UniqueConstraint(columnNames = "user_id")})
//public class parent_graph {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@NotBlank
//	@Column(name = "user_id")
//	private long user_id;
//
//	@NotBlank
//	@Column(name = "updated_at")
//	protected java.sql.Timestamp updatedAtTime;
//
//	@NotBlank
//	@Column(name = "created_at")
//	protected java.sql.Timestamp createdAtTime;
//
////親チャートとくっつける
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
//	private Search parent_graphs;

