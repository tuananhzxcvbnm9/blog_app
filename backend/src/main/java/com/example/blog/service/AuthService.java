package com.example.blog.service;

import com.example.blog.dto.auth.AuthResponse;
import com.example.blog.dto.auth.LoginRequest;
import com.example.blog.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
