package com.example.blogapp.repository;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.CommentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPost_IdAndStatus(Long postId, CommentStatus status, Pageable pageable);
}
