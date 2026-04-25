package com.example.blog.service;

import com.example.blog.dto.comment.CommentRequest;
import com.example.blog.dto.comment.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse create(Long postId, CommentRequest request, String userEmail);
    Page<CommentResponse> listByPost(Long postId, int page, int size);
    CommentResponse changeVisibility(Long id, boolean visible);
}
