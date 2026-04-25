package com.example.blogapp.mapper;

import com.example.blogapp.dto.comment.CommentResponse;
import com.example.blogapp.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getUsername(),
                comment.getPost().getId(),
                comment.getStatus(),
                comment.getCreatedAt()
        );
    }
}
