package com.example.blog.service;

import com.example.blog.dto.post.PostRequest;
import com.example.blog.dto.post.PostResponse;
import org.springframework.data.domain.Page;

public interface PostService {
    Page<PostResponse> listPublic(String keyword, String category, String tag, int page, int size);
    PostResponse getBySlug(String slug, boolean increaseView);
    PostResponse create(PostRequest request, String username);
    PostResponse update(Long id, PostRequest request);
    void delete(Long id);
}
