package com.example.blogapp.mapper;

import com.example.blogapp.dto.post.PostResponse;
import com.example.blogapp.entity.Post;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getSlug(),
                post.getExcerpt(),
                post.getContent(),
                post.getThumbnail(),
                post.getStatus(),
                post.getAuthor().getUsername(),
                post.getCategory() != null ? post.getCategory().getName() : null,
                post.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toSet()),
                post.getViewCount(),
                post.getLikeCount(),
                post.isFeatured(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
