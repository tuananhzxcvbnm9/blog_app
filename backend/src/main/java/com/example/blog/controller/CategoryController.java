package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.category.*;
import com.example.blog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResponse>> list() { return ApiResponse.<List<CategoryResponse>>builder().success(true).data(categoryService.findAll()).build(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CategoryResponse> create(@RequestBody @Valid CategoryRequest request) { return ApiResponse.<CategoryResponse>builder().success(true).data(categoryService.create(request)).build(); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CategoryResponse> update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) { return ApiResponse.<CategoryResponse>builder().success(true).data(categoryService.update(id, request)).build(); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) { categoryService.delete(id); return ApiResponse.<Void>builder().success(true).build(); }
}
