package com.example.blog.service.impl;

import com.example.blog.dto.post.*;
import com.example.blog.entity.*;
import com.example.blog.entity.enums.PostStatus;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.*;
import com.example.blog.service.PostService;
import com.example.blog.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final PostMapper mapper;

    @Override
    public Page<PostResponse> listPublic(String keyword, String category, String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> posts;
        if (category != null && !category.isBlank()) posts = postRepository.findByStatusAndCategory_Slug(PostStatus.PUBLISHED, category, pageable);
        else if (tag != null && !tag.isBlank()) posts = postRepository.findByStatusAndTags_Slug(PostStatus.PUBLISHED, tag, pageable);
        else posts = postRepository.findByStatusAndTitleContainingIgnoreCase(PostStatus.PUBLISHED, keyword == null ? "" : keyword, pageable);
        return posts.map(mapper::toResponse);
    }

    @Override
    public PostResponse getBySlug(String slug, boolean increaseView) {
        Post post = postRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (increaseView) { post.setViewCount(post.getViewCount() + 1); postRepository.save(post); }
        return mapper.toResponse(post);
    }

    @Override
    public PostResponse create(PostRequest request, String username) {
        User author = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Set<Tag> tags = request.getTagIds() == null ? Set.of() : new HashSet<>(tagRepository.findAllById(request.getTagIds()));
        Post post = Post.builder().title(request.getTitle()).slug(SlugUtil.toSlug(request.getTitle() + "-" + System.currentTimeMillis()))
                .excerpt(request.getExcerpt()).content(request.getContent()).thumbnail(request.getThumbnail())
                .status(request.getStatus()).author(author).category(category).tags(tags).build();
        return mapper.toResponse(postRepository.save(post));
    }

    @Override
    public PostResponse update(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        post.setTitle(request.getTitle()); post.setExcerpt(request.getExcerpt()); post.setContent(request.getContent());
        post.setThumbnail(request.getThumbnail()); post.setStatus(request.getStatus()); post.setCategory(category);
        post.setTags(request.getTagIds() == null ? Set.of() : new HashSet<>(tagRepository.findAllById(request.getTagIds())));
        return mapper.toResponse(postRepository.save(post));
    }

    @Override
    public void delete(Long id) { postRepository.deleteById(id); }
}
