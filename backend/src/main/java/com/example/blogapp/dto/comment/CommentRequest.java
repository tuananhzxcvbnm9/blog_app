package com.example.blogapp.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(@NotBlank String content) {
}
