package com.example.blog.config;

import com.example.blog.entity.*;
import com.example.blog.entity.enums.PostStatus;
import com.example.blog.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").build()));
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));

        User admin = userRepository.findByEmail("admin@blog.local").orElseGet(() -> userRepository.save(User.builder()
                .username("admin").email("admin@blog.local").fullName("System Admin")
                .password(passwordEncoder.encode("Admin@123")).roles(Set.of(adminRole, userRole)).build()));

        Category category = categoryRepository.findBySlug("spring-boot").orElseGet(() -> categoryRepository.save(Category.builder().name("Spring Boot").slug("spring-boot").build()));
        Tag tag = tagRepository.findAll().stream().findFirst().orElseGet(() -> tagRepository.save(Tag.builder().name("Backend").slug("backend").build()));

        if (postRepository.count() == 0) {
            postRepository.save(Post.builder().title("Welcome Blog")
                    .slug("welcome-blog")
                    .excerpt("Demo post")
                    .content("This is seeded post content")
                    .status(PostStatus.PUBLISHED)
                    .author(admin)
                    .category(category)
                    .tags(Set.of(tag))
                    .viewCount(0L)
                    .build());
        }
    }
}
