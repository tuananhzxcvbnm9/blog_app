package com.example.blog.service;

import com.example.blog.dto.post.PostRequest;
import com.example.blog.dto.post.PostResponse;
import org.springframework.data.domain.Page;

public interface PostService {
    PostResponse create(PostRequest request, String userEmail);
    PostResponse update(Long id, PostRequest request);
    void delete(Long id);
    PostResponse getById(Long id, boolean increaseView);
    Page<PostResponse> list(String search, int page, int size);
}
