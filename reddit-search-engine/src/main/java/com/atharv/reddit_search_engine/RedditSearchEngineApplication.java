package com.atharv.reddit_search_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedditSearchEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditSearchEngineApplication.class, args);
	}

}
