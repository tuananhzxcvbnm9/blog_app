package com.example.blog.repository;

import com.example.blog.entity.Post;
import com.example.blog.entity.enums.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findBySlug(String slug);
    Page<Post> findByStatusAndTitleContainingIgnoreCase(PostStatus status, String keyword, Pageable pageable);
    Page<Post> findByStatusAndCategory_Slug(PostStatus status, String categorySlug, Pageable pageable);
    Page<Post> findByStatusAndTags_Slug(PostStatus status, String tagSlug, Pageable pageable);
}
