package com.example.blog.controller;

import com.example.blog.dto.common.ApiResponse;
import com.example.blog.dto.post.PostRequest;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public ApiResponse<?> list(@RequestParam(required = false) String search,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder().success(true).data(service.list(search, page, size)).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> detail(@PathVariable Long id) {
        return ApiResponse.builder().success(true).data(service.getById(id, true)).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> create(@Valid @RequestBody PostRequest request, Authentication auth) {
        return ApiResponse.builder().success(true).message("Post created").data(service.create(request, auth.getName())).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> update(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        return ApiResponse.builder().success(true).message("Post updated").data(service.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.builder().success(true).message("Post deleted").build();
    }
}
