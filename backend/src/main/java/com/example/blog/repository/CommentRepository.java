package com.example.blog.repository;

import com.example.blog.entity.Comment;
import com.example.blog.entity.enums.CommentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPost_IdAndStatus(Long postId, CommentStatus status, Pageable pageable);
}
