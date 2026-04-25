package com.example.blogapp.controller;

import com.example.blogapp.dto.auth.LoginRequest;
import com.example.blogapp.dto.auth.RegisterRequest;
import com.example.blogapp.dto.common.ApiResponse;
import com.example.blogapp.service.AuthService;
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
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Register successful").data(authService.register(request)).build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Login successful").data(authService.login(request)).build());
    }
}
