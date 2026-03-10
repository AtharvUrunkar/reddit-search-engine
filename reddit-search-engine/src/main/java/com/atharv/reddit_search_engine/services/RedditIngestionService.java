package com.atharv.reddit_search_engine.services;

import com.atharv.reddit_search_engine.model.Post;
import com.atharv.reddit_search_engine.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedditIngestionService {

	private final PostRepository postRepository;
	private final RestTemplate restTemplate;

	public RedditIngestionService(PostRepository postRepository) {
		this.postRepository = postRepository;
		this.restTemplate = new RestTemplate();
	}

	public List<Post> fetchPosts(String subreddit) {

		String url = "https://www.reddit.com/r/" + subreddit + ".json";

		Map response = restTemplate.getForObject(url, Map.class);

		Map data = (Map) response.get("data");

		List children = (List) data.get("children");

		List<Post> posts = new ArrayList<>();

		for (Object child : children) {

			Map childMap = (Map) child;
			Map postData = (Map) childMap.get("data");

			String redditId = (String) postData.get("id");

			// 🔹 Skip duplicate posts
			if (postRepository.existsByRedditPostId(redditId)) {
				continue;
			}

			Post post = new Post();

			post.setRedditPostId(redditId);
			post.setTitle((String) postData.get("title"));
			post.setBody((String) postData.get("selftext"));
			post.setAuthor((String) postData.get("author"));
			post.setSubreddit(subreddit);

			Number score = (Number) postData.get("score");
			if (score != null) {
				post.setScore(score.intValue());
			}

			Number createdUtc = (Number) postData.get("created_utc");
			if (createdUtc != null) {
				post.setCreatedUtc(Instant.ofEpochSecond(createdUtc.longValue()));
			}

			post.setFetchedAt(Instant.now());

			posts.add(post);
		}

		return postRepository.saveAll(posts);
	}
}