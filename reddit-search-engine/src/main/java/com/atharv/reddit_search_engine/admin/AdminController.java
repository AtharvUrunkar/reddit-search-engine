package com.atharv.reddit_search_engine.admin;

import com.atharv.reddit_search_engine.services.RedditIngestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final RedditIngestionService ingestionService;

	public AdminController(RedditIngestionService ingestionService) {
		this.ingestionService = ingestionService;
	}

	@PostMapping("/ingest-all")
	public String ingestAll() {

		List<String> subreddits = List.of(
				"java",
				"spring",
				"programming",
				"learnprogramming",
				"coding",
				"webdev",
				"devops",
				"backend",
				"microservices",
				"softwarearchitecture",
				"MachineLearning",
				"learnmachinelearning",
				"deeplearning",
				"datascience",
				"artificial",
				"technology",
				"opensource",
				"linux",
				"cloudcomputing",
				"docker",
				"kubernetes",
				"startups",
				"engineering",
				"sysadmin"
		);

		for (String subreddit : subreddits) {
			ingestionService.fetchPosts(subreddit);
		}

		return "Ingestion completed for all subreddits";
	}
}