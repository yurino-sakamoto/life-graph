package com.lifegraph.team20.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchGraphInfo {

    private String username;

	private Long user_id;

	private Timestamp created_at;

	private Timestamp updated_at;
}



















//package com.lifegraph.team20.payload.request;
//
//import java.sql.Timestamp;
//
//import lombok.Data;
//
//@Data
//public class SearchGraphInfo {
//	private String username;
//	private int user_id;
//	private Timestamp created_at;
//	private Timestamp updated_at;
//
//		public SearchGraphInfo(String username, int user_id, Timestamp created_at, Timestamp updated_at) {
//			this.setUsername(username);
//			this.setUser_id(user_id);
//			this.setCreated_at(created_at);
//			this.setUpdated_at(updated_at);
//		}
// }
//
//	public String getName() {
//		return username;
//	}
//
//	public void setName(String username) {
//		this.username = username;
//	}
//
//	public int getId() {
//		return user_id;
//	}
//
//	public void setId(int user_id) {
//		this.user_id = user_id;
//	}
//
//	public Timestamp getCreated() {
//		return created_at;
//	}
//
//	public void setCreated(Timestamp created_at) {
//		this.created_at = created_at;
//	}
//
//	public Timestamp getUpdated() {
//		return updated_at ;
//
//	}
//
//	public void setUpdated(Timestamp updated_at) {
//		this.updated_at = updated_at;
//	}
//}