package com.atharv.reddit_search_engine.services;

import com.atharv.reddit_search_engine.model.Post;
import com.atharv.reddit_search_engine.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	private final PostRepository postRepository;

	public SearchService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Page<Post> search(String keyword, Pageable pageable) {
		return postRepository.searchPosts(keyword, pageable);
	}
}