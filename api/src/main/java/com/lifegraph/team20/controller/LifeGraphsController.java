package com.lifegraph.team20.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.payload.request.RegisterRequest;
import com.lifegraph.team20.payload.response.Account;
import com.lifegraph.team20.payload.response.ChildGraphReference;
import com.lifegraph.team20.payload.response.SearchResponse;
import com.lifegraph.team20.security.services.LifeGraphService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LifeGraphsController {


	@Autowired
	private LifeGraphService service;

	/*
	 * 登録（参照）API
	 */
	@PostMapping(value = "/life-graphs")
	public ResponseEntity<Void> postContent(@RequestBody @Valid RegisterRequest request) throws URISyntaxException {
		service.register(request);
		return ResponseEntity.created( new URI("/life-graphs/" + request.getParentId())).build();
	}


	/*
	 * 削除API
	 */
	// TODO 管理者権限関連の設定
	@DeleteMapping(value = "/life-graphs/{parentId}")
	public ResponseEntity<Void> deleteTable(@PathVariable("parentId") long parentId) {
		service.deleteTable(parentId);
		return ResponseEntity.noContent().build();
	}

	/*
	 *  アカウント参照API
	 */

	@GetMapping("/auth/accounts/{id}")
	public ResponseEntity<Account> accountReference(@PathVariable("id") Integer id) {
		Account accountRef = service.selectUserInfo(id);
		return ResponseEntity.ok(accountRef);
	}

	/*
	 * 人生グラフ参照API
	 */
	@GetMapping("/life-graphs/{userId}")
	public ResponseEntity<List<ChildGraphReference>> lifeGraphReference(@PathVariable("userId") Integer userId) {
		List<ChildGraphReference> childRef = service.selectChildRef(userId);
		return ResponseEntity.ok(childRef);
	}

	/*
	 * 人生グラフ検索API
	 */
	@GetMapping("/life-graphs")
	public ResponseEntity<List<SearchResponse>> SearchGraphInfos(@RequestParam("likeName") Optional<String> likeName,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Optional<Date> startDate,
			@RequestParam("finishDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Optional<Date> finishDate) {
		List<SearchResponse> res = service.search(likeName, startDate, finishDate);
		return ResponseEntity.ok(res);
	}
}