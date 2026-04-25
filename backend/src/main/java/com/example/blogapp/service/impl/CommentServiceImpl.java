package com.example.blogapp.service.impl;

import com.example.blogapp.dto.comment.CommentRequest;
import com.example.blogapp.dto.comment.CommentResponse;
import com.example.blogapp.dto.common.PageResponse;
import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.CommentStatus;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.mapper.CommentMapper;
import com.example.blogapp.repository.CommentRepository;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse addComment(Long postId, CommentRequest request, String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.content())
                .author(user)
                .post(post)
                .status(CommentStatus.VISIBLE)
                .build();
        return commentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public PageResponse<CommentResponse> listVisibleByPost(Long postId, int page, int size) {
        var result = commentRepository.findByPost_IdAndStatus(postId, CommentStatus.VISIBLE, PageRequest.of(page, size));
        return PageResponse.<CommentResponse>builder()
                .content(result.stream().map(commentMapper::toResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    @Override
    public CommentResponse changeStatus(Long commentId, boolean visible) {
        var comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setStatus(visible ? CommentStatus.VISIBLE : CommentStatus.HIDDEN);
        return commentMapper.toResponse(commentRepository.save(comment));
    }
}
