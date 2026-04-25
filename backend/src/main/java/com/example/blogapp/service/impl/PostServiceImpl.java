package com.example.blogapp.service.impl;

import com.example.blogapp.dto.common.PageResponse;
import com.example.blogapp.dto.post.PostRequest;
import com.example.blogapp.dto.post.PostResponse;
import com.example.blogapp.entity.Post;
import com.example.blogapp.entity.PostStatus;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.mapper.PostMapper;
import com.example.blogapp.repository.CategoryRepository;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.repository.TagRepository;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.service.PostService;
import com.example.blogapp.util.SlugUtil;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostMapper postMapper;

    @Override
    public PageResponse<PostResponse> listPublicPosts(String query, String categorySlug, String tagSlug, int page, int size, String sort) {
        Sort sortObj = Sort.by(sort.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC, sort.replace("-", ""));
        Pageable pageable = PageRequest.of(page, size, sortObj);

        var result = postRepository.findByStatusAndTitleContainingIgnoreCaseAndCategory_SlugContainingIgnoreCaseAndTags_SlugContainingIgnoreCase(
                PostStatus.PUBLISHED,
                query == null ? "" : query,
                categorySlug == null ? "" : categorySlug,
                tagSlug == null ? "" : tagSlug,
                pageable
        );

        return PageResponse.<PostResponse>builder()
                .content(result.stream().map(postMapper::toResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    @Override
    @Transactional
    public PostResponse getPublicPostBySlug(String slug) {
        Post post = postRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (post.getStatus() != PostStatus.PUBLISHED) {
            throw new ResourceNotFoundException("Post not published");
        }
        post.setViewCount(post.getViewCount() + 1);
        return postMapper.toResponse(post);
    }

    @Override
    public PostResponse create(PostRequest request, String userEmail) {
        var author = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Post post = new Post();
        applyRequest(post, request);
        post.setAuthor(author);
        return postMapper.toResponse(postRepository.save(post));
    }

    @Override
    public PostResponse update(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        applyRequest(post, request);
        return postMapper.toResponse(postRepository.save(post));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse togglePublish(Long id, boolean published) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.setStatus(published ? PostStatus.PUBLISHED : PostStatus.DRAFT);
        return postMapper.toResponse(postRepository.save(post));
    }

    @Override
    public PostResponse incrementLike(String slug) {
        Post post = postRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.setLikeCount(post.getLikeCount() + 1);
        return postMapper.toResponse(postRepository.save(post));
    }

    private void applyRequest(Post post, PostRequest request) {
        post.setTitle(request.title());
        post.setSlug(SlugUtil.toSlug(request.title()));
        post.setExcerpt(request.excerpt());
        post.setContent(request.content());
        post.setThumbnail(request.thumbnail());
        post.setStatus(request.status());
        post.setFeatured(request.featured());
        post.setCategory(request.categoryId() == null ? null : categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));
        post.setTags(request.tagIds() == null ? new HashSet<>() : new HashSet<>(tagRepository.findByIdIn(request.tagIds().stream().toList())));
    }
}
