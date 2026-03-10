package com.atharv.reddit_search_engine.controller;

import com.atharv.reddit_search_engine.model.Post;
import com.atharv.reddit_search_engine.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;

	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public Page<Post> search(
			@RequestParam String q,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "40") int size
	) {

		return searchService.search(q, PageRequest.of(page, size));
	}
}