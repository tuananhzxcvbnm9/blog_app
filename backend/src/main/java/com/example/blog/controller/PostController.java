package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.post.*;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ApiResponse<Page<PostResponse>> list(@RequestParam(defaultValue = "") String keyword,
                                                @RequestParam(required = false) String category,
                                                @RequestParam(required = false) String tag,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<PostResponse>>builder().success(true).data(postService.listPublic(keyword, category, tag, page, size)).build();
    }

    @GetMapping("/{slug}")
    public ApiResponse<PostResponse> detail(@PathVariable String slug) {
        return ApiResponse.<PostResponse>builder().success(true).data(postService.getBySlug(slug, true)).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<PostResponse> create(@RequestBody @Valid PostRequest request, Authentication auth) {
        return ApiResponse.<PostResponse>builder().success(true).data(postService.create(request, auth.getName())).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<PostResponse> update(@PathVariable Long id, @RequestBody @Valid PostRequest request) {
        return ApiResponse.<PostResponse>builder().success(true).data(postService.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }
}
