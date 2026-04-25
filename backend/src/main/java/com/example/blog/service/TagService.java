package com.example.blog.service;

import com.example.blog.dto.tag.TagRequest;
import com.example.blog.dto.tag.TagResponse;

import java.util.List;

public interface TagService {
    List<TagResponse> findAll();
    TagResponse create(TagRequest request);
    TagResponse update(Long id, TagRequest request);
    void delete(Long id);
}
