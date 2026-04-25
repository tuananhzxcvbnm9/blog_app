package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.comment.*;
import com.example.blog.entity.enums.CommentStatus;
import com.example.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ApiResponse<CommentResponse> add(@RequestBody @Valid CommentRequest request, Authentication auth) {
        return ApiResponse.<CommentResponse>builder().success(true).data(commentService.add(request, auth.getName())).build();
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<Page<CommentResponse>> listByPost(@PathVariable Long postId,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<CommentResponse>>builder().success(true).data(commentService.listByPost(postId, page, size)).build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CommentResponse> updateStatus(@PathVariable Long id, @RequestParam CommentStatus status) {
        return ApiResponse.<CommentResponse>builder().success(true).data(commentService.updateStatus(id, status)).build();
    }
}
