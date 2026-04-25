package com.example.blog.dto.comment;

import com.example.blog.entity.enums.CommentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data @Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private Long postId;
    private CommentStatus status;
    private Instant createdAt;
}
