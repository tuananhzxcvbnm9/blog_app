package com.example.blogapp.repository;

import com.example.blogapp.entity.Post;
import com.example.blogapp.entity.PostStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findBySlug(String slug);

    Page<Post> findByStatusAndTitleContainingIgnoreCaseAndCategory_SlugContainingIgnoreCaseAndTags_SlugContainingIgnoreCase(
            PostStatus status,
            String title,
            String categorySlug,
            String tagSlug,
            Pageable pageable
    );

    Page<Post> findByStatus(PostStatus status, Pageable pageable);
}
