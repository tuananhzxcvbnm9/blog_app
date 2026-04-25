package com.example.blogapp.dto.user;

import java.util.Set;

public record UserProfileResponse(Long id, String username, String email, String fullName, String bio, Set<String> roles) {
}
