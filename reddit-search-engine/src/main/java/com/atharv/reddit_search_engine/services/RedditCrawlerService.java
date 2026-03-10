package com.atharv.reddit_search_engine.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedditCrawlerService {

	private final com.atharv.reddit_search_engine.services.RedditIngestionService ingestionService;

	public RedditCrawlerService(com.atharv.reddit_search_engine.services.RedditIngestionService ingestionService) {
		this.ingestionService = ingestionService;
	}

	private final List<String> subreddits = List.of(
			"java",
			"programming",
			"MachineLearning",
			"datascience",
			"webdev",
			"devops",
			"linux",
			"opensource",
			"cloudcomputing"
	);

	@Scheduled(fixedRate = 600000) // every 10 minutes
	public void crawlReddit() {

		System.out.println("Starting Reddit crawl...");

		for (String subreddit : subreddits) {

			try {

				ingestionService.fetchPosts(subreddit);

				// wait before next request
				Thread.sleep(2000);

			} catch (Exception e) {
				System.out.println("Failed to crawl: " + subreddit);
			}
		}

		System.out.println("Reddit crawl completed");
	}
}