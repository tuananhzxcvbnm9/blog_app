package com.example.blog.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TagDto {
    private Long id;
    @NotBlank
    private String name;
    private String slug;
}
