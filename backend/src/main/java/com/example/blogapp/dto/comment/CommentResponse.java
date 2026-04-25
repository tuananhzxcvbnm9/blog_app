package com.example.blogapp.dto.comment;

import com.example.blogapp.entity.CommentStatus;
import java.time.Instant;

public record CommentResponse(Long id, String content, String author, Long postId, CommentStatus status, Instant createdAt) {
}
