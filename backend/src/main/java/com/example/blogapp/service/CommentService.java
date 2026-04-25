package com.example.blogapp.service;

import com.example.blogapp.dto.comment.CommentRequest;
import com.example.blogapp.dto.comment.CommentResponse;
import com.example.blogapp.dto.common.PageResponse;

public interface CommentService {
    CommentResponse addComment(Long postId, CommentRequest request, String userEmail);
    PageResponse<CommentResponse> listVisibleByPost(Long postId, int page, int size);
    CommentResponse changeStatus(Long commentId, boolean visible);
}
