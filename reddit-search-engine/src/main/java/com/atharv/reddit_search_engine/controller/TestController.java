package com.atharv.reddit_search_engine.controller;


import com.atharv.reddit_search_engine.model.Post;
import com.atharv.reddit_search_engine.repository.PostRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/test")
public class TestController {

	private final PostRepository postRepository;

	public TestController(PostRepository postRepository){
		this.postRepository  = postRepository;
	}

	@PostMapping("/post")
	public Post createTestPost(){
		Post post = new Post();

		post.setRedditPostId("test123");
		post.setSubreddit("java");
		post.setBody("Testing database save");
		post.setAuthor("atharv");
		post.setScore(10);
		post.setCreatedUtc(Instant.now());
		post.setFetchedAt(Instant.now());

		return postRepository.save(post);
	}


}
