package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Category;
import com.example.blogapp.entity.Tag;
import com.example.blogapp.repository.CategoryRepository;
import com.example.blogapp.repository.TagRepository;
import com.example.blogapp.service.TaxonomyService;
import com.example.blogapp.util.SlugUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxonomyServiceImpl implements TaxonomyService {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Override
    public List<String> listCategories() {
        return categoryRepository.findAll().stream().map(Category::getName).toList();
    }

    @Override
    public List<String> listTags() {
        return tagRepository.findAll().stream().map(Tag::getName).toList();
    }

    @Override
    public String createCategory(String name) {
        Category category = Category.builder().name(name).slug(SlugUtil.toSlug(name)).build();
        return categoryRepository.save(category).getName();
    }

    @Override
    public String createTag(String name) {
        Tag tag = Tag.builder().name(name).slug(SlugUtil.toSlug(name)).build();
        return tagRepository.save(tag).getName();
    }
}
