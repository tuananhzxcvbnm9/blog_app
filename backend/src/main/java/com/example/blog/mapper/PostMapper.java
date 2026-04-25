package com.example.blog.mapper;

import com.example.blog.dto.post.PostResponse;
import com.example.blog.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .slug(post.getSlug())
                .excerpt(post.getExcerpt())
                .content(post.getContent())
                .thumbnail(post.getThumbnail())
                .status(post.getStatus())
                .author(post.getAuthor().getUsername())
                .category(post.getCategory().getName())
                .tags(post.getTags().stream().map(t -> t.getName()).toList())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
