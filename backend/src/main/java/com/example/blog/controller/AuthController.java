package com.example.blog.controller;

import com.example.blog.dto.auth.LoginRequest;
import com.example.blog.dto.auth.RegisterRequest;
import com.example.blog.dto.common.ApiResponse;
import com.example.blog.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Registered").data(authService.register(request)).build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Login success").data(authService.login(request)).build());
    }
}
