package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.user.*;
import com.example.blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserProfileResponse> me(Authentication auth) {
        return ApiResponse.<UserProfileResponse>builder().success(true).data(userService.me(auth.getName())).build();
    }

    @PutMapping("/me")
    public ApiResponse<UserProfileResponse> updateMe(Authentication auth, @RequestBody @Valid UpdateProfileRequest request) {
        return ApiResponse.<UserProfileResponse>builder().success(true).data(userService.updateMyProfile(auth.getName(), request)).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserProfileResponse>> list() {
        return ApiResponse.<List<UserProfileResponse>>builder().success(true).data(userService.listUsers()).build();
    }
}
