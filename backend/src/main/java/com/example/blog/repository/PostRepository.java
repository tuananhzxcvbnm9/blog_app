package com.example.blog.repository;

import com.example.blog.entity.Post;
import com.example.blog.entity.enums.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByStatusAndTitleContainingIgnoreCase(PostStatus status, String title, Pageable pageable);
    Page<Post> findByStatus(PostStatus status, Pageable pageable);
}
