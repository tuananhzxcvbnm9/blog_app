package com.example.blog.dto.post;

import com.example.blog.entity.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    @NotBlank private String title;
    @NotBlank private String excerpt;
    @NotBlank private String content;
    private String thumbnail;
    @NotNull private PostStatus status;
    @NotNull private Long categoryId;
    private List<Long> tagIds;
}
