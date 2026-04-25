package com.example.blog.service.impl;

import com.example.blog.dto.category.CategoryDto;
import com.example.blog.entity.Category;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category category = repository.save(Category.builder().name(dto.getName()).slug(SlugUtil.toSlug(dto.getName())).build());
        return map(category);
    }

    @Override
    public List<CategoryDto> getAll() { return repository.findAll().stream().map(this::map).toList(); }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        category.setName(dto.getName());
        category.setSlug(SlugUtil.toSlug(dto.getName()));
        return map(repository.save(category));
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    private CategoryDto map(Category c) {
        CategoryDto dto = new CategoryDto();
        dto.setId(c.getId()); dto.setName(c.getName()); dto.setSlug(c.getSlug());
        return dto;
    }
}
