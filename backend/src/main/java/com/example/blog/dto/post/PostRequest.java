package com.example.blog.dto.post;

import com.example.blog.entity.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PostRequest {
    @NotBlank
    private String title;
    private String excerpt;
    @NotBlank
    private String content;
    private String thumbnail;
    @NotNull
    private PostStatus status;
    private Long categoryId;
    private Set<Long> tagIds;
}
