package com.atharv.reddit_search_engine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "posts")
@Getter
@Setter

public class Post{
	 @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	 @Column(name = "reddit_post_id",nullable = false,unique = true)
	private String redditPostId;

	 @Column(nullable = false)
	private String subreddit;

	@Column(columnDefinition = "TEXT")
	private String title;

	@Column(columnDefinition = "TEXT")
	private String body;

	 private String author;

	 private Integer score;

	 private Instant createdUtc;

	 private Instant fetchedAt;

	@Transient
	public String getRedditUrl() {
		return "https://www.reddit.com/r/" + subreddit + "/comments/" + redditPostId;
	}

}


