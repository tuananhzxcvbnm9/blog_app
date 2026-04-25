package com.example.blog.service;

import com.example.blog.dto.category.CategoryRequest;
import com.example.blog.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
}
