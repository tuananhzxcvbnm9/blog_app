package com.example.blogapp.config;

import com.example.blogapp.entity.*;
import com.example.blogapp.repository.*;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        if (roleRepository.count() == 0) {
            roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN).build());
            roleRepository.save(Role.builder().name(RoleName.ROLE_USER).build());
        }
        if (userRepository.count() == 0) {
            var adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow();
            var userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow();

            var admin = User.builder()
                    .username("admin")
                    .email("admin@blog.local")
                    .password(passwordEncoder.encode("Admin@123"))
                    .fullName("System Admin")
                    .roles(Set.of(adminRole, userRole))
                    .build();
            var user = User.builder()
                    .username("johndoe")
                    .email("john@blog.local")
                    .password(passwordEncoder.encode("User@123"))
                    .fullName("John Doe")
                    .roles(Set.of(userRole))
                    .build();
            userRepository.save(admin);
            userRepository.save(user);

            var cat = categoryRepository.save(Category.builder().name("Tech").slug("tech").build());
            var tag = tagRepository.save(Tag.builder().name("Spring Boot").slug("spring-boot").build());

            Post post = Post.builder()
                    .title("Welcome to Blog App")
                    .slug("welcome-to-blog-app")
                    .excerpt("Ứng dụng blog demo fullstack")
                    .content("Nội dung bài viết mẫu cho dự án demo.")
                    .status(PostStatus.PUBLISHED)
                    .author(admin)
                    .category(cat)
                    .tags(Set.of(tag))
                    .featured(true)
                    .build();
            postRepository.save(post);
        }
    }
}
