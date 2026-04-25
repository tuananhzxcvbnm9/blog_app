package com.example.blogapp.dto.user;

import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(@Size(max = 120) String fullName, @Size(max = 255) String bio) {
}
