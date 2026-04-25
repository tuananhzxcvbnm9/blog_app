package com.example.blog.config;

import com.example.blog.entity.*;
import com.example.blog.entity.enums.PostStatus;
import com.example.blog.entity.enums.RoleName;
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
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN).build()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_USER).build()));

        User admin = userRepository.findByUsername("admin").orElseGet(() -> userRepository.save(User.builder()
                .username("admin").email("admin@blog.local").fullName("Admin User")
                .password(passwordEncoder.encode("admin123")).roles(Set.of(adminRole, userRole)).build()));

        categoryRepository.findBySlug("technology").orElseGet(() -> {
            Category c = categoryRepository.save(Category.builder().name("Technology").slug("technology").build());
            Tag t = tagRepository.save(Tag.builder().name("Spring Boot").slug("spring-boot").build());
            postRepository.save(Post.builder().title("Welcome to Blog App").slug("welcome-blog-app")
                    .excerpt("Ứng dụng blog demo fullstack")
                    .content("Nội dung mẫu cho bài viết đầu tiên")
                    .status(PostStatus.PUBLISHED)
                    .author(admin)
                    .category(c)
                    .tags(Set.of(t))
                    .thumbnail("https://picsum.photos/600/300")
                    .viewCount(10L)
                    .build());
            return c;
        });
    }
}
