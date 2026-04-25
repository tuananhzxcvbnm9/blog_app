package com.example.blog.service;

import com.example.blog.dto.comment.CommentRequest;
import com.example.blog.dto.comment.CommentResponse;
import com.example.blog.entity.enums.CommentStatus;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse add(CommentRequest request, String username);
    Page<CommentResponse> listByPost(Long postId, int page, int size);
    CommentResponse updateStatus(Long id, CommentStatus status);
}
