package com.lifegraph.team20.payload.response;

import java.sql.Timestamp;

import com.lifegraph.team20.request.SearchGraphInfo;

import lombok.Data;

@Data
public class SearchResponse {

	private String userName;

	private Long id;

	private Long user_id;

	private Timestamp created_at;

	private Timestamp updated_at;

	public SearchResponse(SearchGraphInfo result) {
		this.userName = result.getUsername();
		this.id = result.getId();
		this.user_id = result.getUser_id();
		this.created_at = result.getCreated_at();
		this.updated_at = result.getUpdated_at();
	}
}