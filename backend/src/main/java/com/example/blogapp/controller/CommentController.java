package com.example.blogapp.controller;

import com.example.blogapp.dto.comment.CommentRequest;
import com.example.blogapp.dto.common.ApiResponse;
import com.example.blogapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/post/{postId}")
    public ApiResponse<Object> listByPost(@PathVariable Long postId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder().success(true).message("Fetched comments")
                .data(commentService.listVisibleByPost(postId, page, size)).build();
    }

    @PostMapping("/post/{postId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<Object> addComment(@PathVariable Long postId, @Valid @RequestBody CommentRequest request, Authentication authentication) {
        return ApiResponse.builder().success(true).message("Added comment")
                .data(commentService.addComment(postId, request, authentication.getName())).build();
    }

    @PatchMapping("/{id}/visibility")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> setVisibility(@PathVariable Long id, @RequestParam boolean visible) {
        return ApiResponse.builder().success(true).message("Updated comment")
                .data(commentService.changeStatus(id, visible)).build();
    }
}
