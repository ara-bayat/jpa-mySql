package com.learning.jpa.repository;

import com.learning.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Derived Query Methods
    List<Post> findByTitleContainingIgnoreCase(String keyword);
    List<Post> findByAuthorEmail(String email);
    List<Post> findByTitleContainingAndAuthorName(String title, String name);
    long countByAuthorId(Long authorId);
    List<Post> findTop5ByOrderByCreatedAtDesc();

    // JPQL
    @Query("SELECT p FROM Post p WHERE SIZE(p.comments) > :min")
    List<Post> findPostsWithMoreThanComments(@Param("min") int min);

    @Query("SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :id")
    Optional<Post> findByIdWithAuthor(@Param("id") Long id);

    // Projection
    @Query("SELECT p.title AS title, p.createdAt AS createdAt FROM Post p")
    List<PostSummary> findAllSummaries();

    interface PostSummary {
        String getTitle();
        LocalDateTime getCreatedAt();
    }

    @Query(
            value = "SELECT p FROM Post p JOIN p.author a WHERE a.name = :name",
            countQuery = "SELECT COUNT(p) FROM Post p JOIN p.author a WHERE a.name = :name"
    )
    Page<Post> findByAuthorName(@Param("name") String name, Pageable pageable);
}