package com.example.blog.dto.auth;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private String username;
    private String role;
}
