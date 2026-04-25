package com.example.blog.service;

import com.example.blog.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);
    List<CategoryDto> getAll();
    CategoryDto update(Long id, CategoryDto dto);
    void delete(Long id);
}
