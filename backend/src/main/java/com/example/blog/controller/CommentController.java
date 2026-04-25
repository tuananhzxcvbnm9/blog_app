package com.example.blog.controller;

import com.example.blog.dto.comment.CommentRequest;
import com.example.blog.dto.common.ApiResponse;
import com.example.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @PostMapping("/post/{postId}")
    public ApiResponse<?> create(@PathVariable Long postId, @Valid @RequestBody CommentRequest request, Authentication auth) {
        return ApiResponse.builder().success(true).data(service.create(postId, request, auth.getName())).build();
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<?> list(@PathVariable Long postId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder().success(true).data(service.listByPost(postId, page, size)).build();
    }

    @PatchMapping("/{id}/visibility")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> toggle(@PathVariable Long id, @RequestParam boolean visible) {
        return ApiResponse.builder().success(true).data(service.changeVisibility(id, visible)).build();
    }
}
