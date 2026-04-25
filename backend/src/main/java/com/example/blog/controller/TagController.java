package com.example.blog.controller;

import com.example.blog.dto.ApiResponse;
import com.example.blog.dto.tag.*;
import com.example.blog.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ApiResponse<List<TagResponse>> list() { return ApiResponse.<List<TagResponse>>builder().success(true).data(tagService.findAll()).build(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<TagResponse> create(@RequestBody @Valid TagRequest request) { return ApiResponse.<TagResponse>builder().success(true).data(tagService.create(request)).build(); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<TagResponse> update(@PathVariable Long id, @RequestBody @Valid TagRequest request) { return ApiResponse.<TagResponse>builder().success(true).data(tagService.update(id, request)).build(); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) { tagService.delete(id); return ApiResponse.<Void>builder().success(true).build(); }
}
