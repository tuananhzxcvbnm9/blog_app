package com.example.blog.mapper;

import com.example.blog.dto.post.PostResponse;
import com.example.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
                .category(post.getCategory() != null ? post.getCategory().getName() : null)
                .tags(post.getTags().stream().map(t -> t.getName()).collect(Collectors.toSet()))
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
