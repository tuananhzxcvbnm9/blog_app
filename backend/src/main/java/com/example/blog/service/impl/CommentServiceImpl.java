package com.example.blog.service.impl;

import com.example.blog.dto.comment.CommentRequest;
import com.example.blog.dto.comment.CommentResponse;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.enums.CommentStatus;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponse create(Long postId, CommentRequest request, String userEmail) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new NotFoundException("User not found"));
        Comment comment = Comment.builder().content(request.getContent()).post(post).author(user).status(CommentStatus.VISIBLE).build();
        return toResponse(commentRepository.save(comment));
    }

    @Override
    public Page<CommentResponse> listByPost(Long postId, int page, int size) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        return commentRepository.findByPost(post, PageRequest.of(page, size)).map(this::toResponse);
    }

    @Override
    public CommentResponse changeVisibility(Long id, boolean visible) {
        Comment c = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        c.setStatus(visible ? CommentStatus.VISIBLE : CommentStatus.HIDDEN);
        return toResponse(commentRepository.save(c));
    }

    private CommentResponse toResponse(Comment c) {
        return CommentResponse.builder().id(c.getId()).content(c.getContent()).author(c.getAuthor().getUsername())
                .postId(c.getPost().getId()).status(c.getStatus()).createdAt(c.getCreatedAt()).build();
    }
}
