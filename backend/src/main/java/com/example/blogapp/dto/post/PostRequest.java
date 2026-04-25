package com.example.blogapp.dto.post;

import com.example.blogapp.entity.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record PostRequest(
        @NotBlank @Size(max = 200) String title,
        @Size(max = 300) String excerpt,
        @NotBlank String content,
        String thumbnail,
        @NotNull PostStatus status,
        Long categoryId,
        Set<Long> tagIds,
        boolean featured
) {
}
