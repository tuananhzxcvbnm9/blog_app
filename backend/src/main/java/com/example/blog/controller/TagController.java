package com.example.blog.controller;

import com.example.blog.dto.common.ApiResponse;
import com.example.blog.dto.tag.TagDto;
import com.example.blog.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService service;

    @GetMapping
    public ApiResponse<?> list() { return ApiResponse.builder().success(true).data(service.getAll()).build(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> create(@Valid @RequestBody TagDto dto) { return ApiResponse.builder().success(true).data(service.create(dto)).build(); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> update(@PathVariable Long id, @Valid @RequestBody TagDto dto) { return ApiResponse.builder().success(true).data(service.update(id, dto)).build(); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> delete(@PathVariable Long id) { service.delete(id); return ApiResponse.builder().success(true).build(); }
}
