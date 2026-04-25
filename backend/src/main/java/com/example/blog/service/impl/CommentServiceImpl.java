package com.example.blog.service.impl;

import com.example.blog.dto.comment.*;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.enums.CommentStatus;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.*;
import com.example.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public CommentResponse add(CommentRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Comment saved = commentRepository.save(Comment.builder().content(request.getContent()).status(CommentStatus.VISIBLE).author(user).post(post).build());
        return toRes(saved);
    }

    @Override
    public Page<CommentResponse> listByPost(Long postId, int page, int size) {
        return commentRepository.findByPost_IdAndStatus(postId, CommentStatus.VISIBLE, PageRequest.of(page, size, Sort.by("createdAt").descending())).map(this::toRes);
    }

    @Override
    public CommentResponse updateStatus(Long id, CommentStatus status) {
        Comment c = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        c.setStatus(status);
        return toRes(commentRepository.save(c));
    }

    private CommentResponse toRes(Comment c) {
        return CommentResponse.builder().id(c.getId()).content(c.getContent()).author(c.getAuthor().getUsername())
                .postId(c.getPost().getId()).status(c.getStatus()).createdAt(c.getCreatedAt()).build();
    }
}
