package com.example.blogapp.service;

import com.example.blogapp.dto.common.PageResponse;
import com.example.blogapp.dto.post.PostRequest;
import com.example.blogapp.dto.post.PostResponse;

public interface PostService {
    PageResponse<PostResponse> listPublicPosts(String query, String categorySlug, String tagSlug, int page, int size, String sort);
    PostResponse getPublicPostBySlug(String slug);
    PostResponse create(PostRequest request, String userEmail);
    PostResponse update(Long id, PostRequest request);
    void delete(Long id);
    PostResponse togglePublish(Long id, boolean published);
    PostResponse incrementLike(String slug);
}
