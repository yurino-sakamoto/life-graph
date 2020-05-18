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






//@Data
//@Entity
//@Table (name = "Parent_Chart",
//        uniqueConstraints = {
//        @UniqueConstraint(columnNames = "user_id")})
//public class ParentLifeGraph {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//    @NotBlank
//    @Column(name = "user_id")
//    private long user_id;
//    @NotBlank
//    @Column(name = "created_at")
//    protected java.sql.Timestamp createdAtTime;
//    @NotBlank
//    @Column(name = "updated_at")
//    protected java.sql.Timestamp updatedAtTime;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval=true)
//    @JsonIgnore
//    static
//     List<LifeGraph> lifeGraph;
//    public ParentLifeGraph(long user_id, java.sql.Timestamp createdAtTime, java.sql.Timestamp updatedAtTime) {
//        this.user_id = user_id;
//        this.createdAtTime = createdAtTime;
//        this.updatedAtTime = updatedAtTime;
//    }
//    public static List<LifeGraph> getLifeGraph() {
//        return lifeGraph;
//    }
//    public void setLifeGraph(List<LifeGraph> lifeGraph) {
//        this.lifeGraph = lifeGraph;
//    }
//}
