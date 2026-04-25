package com.example.blog.service.impl;

import com.example.blog.dto.tag.*;
import com.example.blog.entity.Tag;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository repo;

    @Override public List<TagResponse> findAll() { return repo.findAll().stream().map(t -> TagResponse.builder().id(t.getId()).name(t.getName()).slug(t.getSlug()).build()).toList(); }
    @Override public TagResponse create(TagRequest request) {
        Tag t = repo.save(Tag.builder().name(request.getName()).slug(SlugUtil.toSlug(request.getName())).build());
        return TagResponse.builder().id(t.getId()).name(t.getName()).slug(t.getSlug()).build();
    }
    @Override public TagResponse update(Long id, TagRequest request) {
        Tag t = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        t.setName(request.getName()); t.setSlug(SlugUtil.toSlug(request.getName())); repo.save(t);
        return TagResponse.builder().id(t.getId()).name(t.getName()).slug(t.getSlug()).build();
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
}
