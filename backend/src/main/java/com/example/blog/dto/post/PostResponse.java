package com.example.blog.dto.post;

import com.example.blog.entity.enums.PostStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
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
    private Set<String> tags;
    private long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
