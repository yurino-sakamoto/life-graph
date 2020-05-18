//package com.lifegraph.team20.search;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotBlank;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table (name = "Parent_graph",
//uniqueConstraints = {
//		@UniqueConstraint(columnNames = "user_id")})
//public class parent_graphs {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private long id;
//
//	@NotBlank
//	@Column(name = "user_id")
//	private long user_id;
//
//	@NotBlank
//	@Column(name = "updated_at")
//	protected java.sql.Timestamp updatedAtTime;
//
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval=true)
//	@JsonIgnore
//	static
//	List<Search> Search;
//	public parent_graphs(long user_id, java.sql.Timestamp updatedAtTime) {
//		this.user_id = user_id;
//		this.updatedAtTime = updatedAtTime;
//	}
//	public static List<Search> getSearch() {
//		return Search;
//	}
//	public void setSearch(List<Search> Search) {
//		this.Search = Search;
//	}
//}