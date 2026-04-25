package com.example.blogapp.dto.post;

import com.example.blogapp.entity.PostStatus;
import java.time.Instant;
import java.util.Set;

public record PostResponse(
        Long id,
        String title,
        String slug,
        String excerpt,
        String content,
        String thumbnail,
        PostStatus status,
        String author,
        String category,
        Set<String> tags,
        Long viewCount,
        Long likeCount,
        boolean featured,
        Instant createdAt,
        Instant updatedAt
) {
}
