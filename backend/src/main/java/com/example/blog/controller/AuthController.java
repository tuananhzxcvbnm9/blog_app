package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.auth.*;
import com.example.blog.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ApiResponse.<AuthResponse>builder().success(true).message("Register success").data(authService.register(request)).build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.<AuthResponse>builder().success(true).message("Login success").data(authService.login(request)).build();
    }
}
