package com.example.blogapp.controller;

import com.example.blogapp.dto.common.ApiResponse;
import com.example.blogapp.dto.post.PostRequest;
import com.example.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ApiResponse<Object> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "-createdAt") String sort
    ) {
        return ApiResponse.builder().success(true).message("Fetched posts")
                .data(postService.listPublicPosts(q, category, tag, page, size, sort)).build();
    }

    @GetMapping("/{slug}")
    public ApiResponse<Object> detail(@PathVariable String slug) {
        return ApiResponse.builder().success(true).message("Fetched post").data(postService.getPublicPostBySlug(slug)).build();
    }

    @PatchMapping("/{slug}/like")
    public ApiResponse<Object> like(@PathVariable String slug) {
        return ApiResponse.builder().success(true).message("Liked post").data(postService.incrementLike(slug)).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> create(@Valid @RequestBody PostRequest request, Authentication auth) {
        return ApiResponse.builder().success(true).message("Created post").data(postService.create(request, auth.getName())).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> update(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        return ApiResponse.builder().success(true).message("Updated post").data(postService.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.builder().success(true).message("Deleted post").build();
    }
}
