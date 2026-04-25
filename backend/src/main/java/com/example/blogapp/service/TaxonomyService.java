package com.example.blogapp.service;

import java.util.List;

public interface TaxonomyService {
    List<String> listCategories();
    List<String> listTags();
    String createCategory(String name);
    String createTag(String name);
}
