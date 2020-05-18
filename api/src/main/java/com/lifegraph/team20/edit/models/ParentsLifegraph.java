package com.lifegraph.team20.edit.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

//Entityデータベースに接続するときの値を持っておく
@Data
@Entity
@Table(name = "Parents_lifegraph")
public class ParentsLifegraph {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;


	private java.sql.Timestamp created_at;
	private java.sql.Timestamp update_at;
	private long user_id;

	//カラムをくっつける（fetch = FetchType.LAZY?）
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user_id")
	private List<ChildLifegraph> Lifegraph;

	public ParentsLifegraph(long id, java.sql.Timestamp created_at,java.sql.Timestamp update_at, long user_id) {
		this.id = id;
		this.created_at = created_at;
		this.update_at = update_at;
		this.user_id = user_id;
	}
	public ParentsLifegraph(long id,java.sql.Timestamp update_at, long user_id) {
		this.id = id;
		this.update_at = update_at;
		this.user_id = user_id;
	}
}

//@PrePersist→
