package com.example.blogapp.controller;

import com.example.blogapp.dto.common.ApiResponse;
import com.example.blogapp.service.TaxonomyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaxonomyController {
    private final TaxonomyService taxonomyService;

    @GetMapping("/api/categories")
    public ApiResponse<Object> categories() {
        return ApiResponse.builder().success(true).message("Fetched categories").data(taxonomyService.listCategories()).build();
    }

    @GetMapping("/api/tags")
    public ApiResponse<Object> tags() {
        return ApiResponse.builder().success(true).message("Fetched tags").data(taxonomyService.listTags()).build();
    }

    @PostMapping("/api/admin/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> createCategory(@RequestParam @NotBlank String name) {
        return ApiResponse.builder().success(true).message("Created category").data(taxonomyService.createCategory(name)).build();
    }

    @PostMapping("/api/admin/tags")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> createTag(@RequestParam @NotBlank String name) {
        return ApiResponse.builder().success(true).message("Created tag").data(taxonomyService.createTag(name)).build();
    }
}
