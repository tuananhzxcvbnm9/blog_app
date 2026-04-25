package com.example.blogapp.controller;

import com.example.blogapp.dto.common.ApiResponse;
import com.example.blogapp.dto.user.UpdateProfileRequest;
import com.example.blogapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<Object> me(Authentication authentication) {
        return ApiResponse.builder().success(true).message("Fetched profile")
                .data(userService.getMyProfile(authentication.getName())).build();
    }

    @PutMapping("/me")
    public ApiResponse<Object> updateMe(Authentication authentication, @Valid @RequestBody UpdateProfileRequest request) {
        return ApiResponse.builder().success(true).message("Updated profile")
                .data(userService.updateMyProfile(authentication.getName(), request)).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder().success(true).message("Fetched users")
                .data(userService.listUsers(page, size)).build();
    }
}
