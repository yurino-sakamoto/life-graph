package com.lifegraph.team20.models;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lifegraph.team20.payload.request.ResisterRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Parent_graphs")
public class Parent_graphs {

	//long→nullの状態がない
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column( name = "user_id")
	private Long userId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Parent_graphs (ResisterRequest resisterReques){
		this.userId=resisterReques.getUserId();
		this.id=resisterReques.getParentId();
	}
}
