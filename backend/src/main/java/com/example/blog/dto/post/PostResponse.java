package com.example.blog.dto.post;

import com.example.blog.entity.enums.PostStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data @Builder
public class PostResponse {
    private Long id;
    private String title;
    private String slug;
    private String excerpt;
    private String content;
    private String thumbnail;
    private PostStatus status;
    private String author;
    private String category;
    private List<String> tags;
    private Long viewCount;
    private Instant createdAt;
    private Instant updatedAt;
}
