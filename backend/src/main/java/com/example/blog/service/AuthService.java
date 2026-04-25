package com.example.blog.service;

import com.example.blog.dto.auth.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
