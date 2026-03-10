package com.atharv.reddit_search_engine.repository;

import com.atharv.reddit_search_engine.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<Post> findByRedditPostId(String redditPostId);

	boolean existsByRedditPostId(String redditPostId);

	@Query(value = """
SELECT * FROM posts
WHERE search_vector @@ plainto_tsquery('english', :keyword)
ORDER BY ts_rank(search_vector, plainto_tsquery('english', :keyword)) DESC
""", nativeQuery = true)
	Page<Post> searchPosts(@Param("keyword") String keyword, Pageable pageable);
	//List<Post> searchPosts(String keyword);
}