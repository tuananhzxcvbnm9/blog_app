package com.example.blog.service;

import com.example.blog.dto.tag.TagDto;

import java.util.List;

public interface TagService {
    TagDto create(TagDto dto);
    List<TagDto> getAll();
    TagDto update(Long id, TagDto dto);
    void delete(Long id);
}
