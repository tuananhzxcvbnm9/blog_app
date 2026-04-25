package com.example.blogapp.dto.auth;

import java.util.Set;

public record AuthResponse(String accessToken, String tokenType, Long userId, String email, String username, Set<String> roles) {
}
