package com.example.blog.service.impl;

import com.example.blog.dto.category.*;
import com.example.blog.entity.Category;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;

    @Override public List<CategoryResponse> findAll() {
        return repo.findAll().stream().map(c -> CategoryResponse.builder().id(c.getId()).name(c.getName()).slug(c.getSlug()).build()).toList();
    }
    @Override public CategoryResponse create(CategoryRequest request) {
        Category saved = repo.save(Category.builder().name(request.getName()).slug(SlugUtil.toSlug(request.getName())).build());
        return CategoryResponse.builder().id(saved.getId()).name(saved.getName()).slug(saved.getSlug()).build();
    }
    @Override public CategoryResponse update(Long id, CategoryRequest request) {
        Category c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        c.setName(request.getName()); c.setSlug(SlugUtil.toSlug(request.getName()));
        repo.save(c);
        return CategoryResponse.builder().id(c.getId()).name(c.getName()).slug(c.getSlug()).build();
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
}
