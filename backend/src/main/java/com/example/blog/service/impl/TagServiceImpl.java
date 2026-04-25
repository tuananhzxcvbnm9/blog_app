package com.example.blog.service.impl;

import com.example.blog.dto.tag.TagDto;
import com.example.blog.entity.Tag;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository repository;

    @Override
    public TagDto create(TagDto dto) {
        Tag tag = repository.save(Tag.builder().name(dto.getName()).slug(SlugUtil.toSlug(dto.getName())).build());
        return map(tag);
    }

    @Override
    public List<TagDto> getAll() { return repository.findAll().stream().map(this::map).toList(); }

    @Override
    public TagDto update(Long id, TagDto dto) {
        Tag tag = repository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found"));
        tag.setName(dto.getName());
        tag.setSlug(SlugUtil.toSlug(dto.getName()));
        return map(repository.save(tag));
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    private TagDto map(Tag t) {
        TagDto dto = new TagDto();
        dto.setId(t.getId()); dto.setName(t.getName()); dto.setSlug(t.getSlug());
        return dto;
    }
}
