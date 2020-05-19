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
//@Table(name="parent_graphs")
//public class Search {
//
//	@Id
//	@Column(name="user_id")
//	@Getter
//	@Setter
//	private String userId;
//
//	@Column(name="updated_at")
//	@Getter
//	@Setter
//	private String updatedAt;
//}

//④
//package com.lifegraph.team20.search;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table (name="users")
//public class Search {
//
//	@Id
//	@Column(name="id")
//	@Getter
//	@Setter
//	private String id;
//
//	@Column(name="name")
//	@Getter
//	@Setter
//	private String name;
//
//	@Getter
//	@Setter
//	@OneToMany
//	@JoinColumn(name="user_id")
//	private  parent_graph Search;
//
//}

//⑤
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
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name="parent_graphs")
//public class Search {
//
//	@Id
//	@Column(name="user_id")
//	@Getter
//	@Setter
//	private String userId;
//
//	@Column(name="updated_at")
//	@Getter
//	@Setter
//	private String updatedAt;
//
//	@Column(name="created_at")
//	@Getter
//	@Setter
//	private String createdAt;
//
//}

//③
//@Entity
//@Table(name="users")
//private class Users {
//
//	@Id
//	@Column(name="id")
//	@Getter
//	@Setter
//	private String id;
//
//	@Column(name="name")
//	@Getter
//	@Setter
//	private String name;
//
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn( name = "id", referencedColumnName = "user_id", insertable=false, updatable=false)
//	private Search parent_graphs;
//}
//

//②
//@Data
//@NoArgsConstructor
//@Entity
//@Table (name = "parent_graph",
//	uniqueConstraints = {
//	@UniqueConstraint(columnNames = "id")})
//
//public class Search {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private long id;
//
//	@Column(name = "name")
//	private String name;
//
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "parent_graphs", orphanRemoval=true)
//	@JsonIgnore
//	static
//	List<parent_graph> Parent_graph;
//
//	public Search (String name) {
//		this.name = name;
//	}
//
//	public static List<parent_graph> getParent_graph() {
//		return Parent_graph;
//	}
//	public void setSearch(List<parent_graph> Parent_graph ) {
//		Search.Parent_graph = Parent_graph;
//	}
//}
