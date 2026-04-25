package com.example.blog.controller;

import com.example.blog.dto.common.ApiResponse;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final CommentService commentService;

    @GetMapping("/users")
    public ApiResponse<?> users() {
        return ApiResponse.builder().success(true).data(userRepository.findAll()).build();
    }

    @PatchMapping("/comments/{id}/hide")
    public ApiResponse<?> hideComment(@PathVariable Long id) {
        return ApiResponse.builder().success(true).data(commentService.changeVisibility(id, false)).build();
    }

    @PatchMapping("/comments/{id}/show")
    public ApiResponse<?> showComment(@PathVariable Long id) {
        return ApiResponse.builder().success(true).data(commentService.changeVisibility(id, true)).build();
    }
}
