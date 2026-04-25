package com.example.blog.service.impl;

import com.example.blog.dto.post.PostRequest;
import com.example.blog.dto.post.PostResponse;
import com.example.blog.entity.*;
import com.example.blog.entity.enums.PostStatus;
import com.example.blog.exception.NotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.*;
import com.example.blog.service.PostService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostMapper mapper;

    @Override
    public PostResponse create(PostRequest request, String userEmail) {
        User author = userRepository.findByEmail(userEmail).orElseThrow(() -> new NotFoundException("Author not found"));
        Post post = new Post();
        applyRequest(post, request);
        post.setAuthor(author);
        return mapper.toResponse(postRepository.save(post));
    }

    @Override
    public PostResponse update(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        applyRequest(post, request);
        return mapper.toResponse(postRepository.save(post));
    }

    @Override
    public void delete(Long id) { postRepository.deleteById(id); }

    @Override
    public PostResponse getById(Long id, boolean increaseView) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        if (increaseView && post.getStatus() == PostStatus.PUBLISHED) {
            post.setViewCount(post.getViewCount() + 1);
            postRepository.save(post);
        }
        return mapper.toResponse(post);
    }

    @Override
    public Page<PostResponse> list(String search, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> posts = (search == null || search.isBlank())
                ? postRepository.findByStatus(PostStatus.PUBLISHED, pageable)
                : postRepository.findByStatusAndTitleContainingIgnoreCase(PostStatus.PUBLISHED, search, pageable);
        return posts.map(mapper::toResponse);
    }

    private void applyRequest(Post post, PostRequest request) {
        post.setTitle(request.getTitle());
        post.setSlug(SlugUtil.toSlug(request.getTitle()));
        post.setExcerpt(request.getExcerpt());
        post.setContent(request.getContent());
        post.setThumbnail(request.getThumbnail());
        post.setStatus(request.getStatus());
        if (post.getViewCount() == 0) post.setViewCount(0L);

        if (request.getCategoryId() != null) {
            post.setCategory(categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found")));
        }
        Set<Tag> tags = new HashSet<>();
        if (request.getTagIds() != null) request.getTagIds().forEach(id -> tags.add(tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found with id " + id))));
        post.setTags(tags);
    }
}
