package com.example.blog.service.impl;

import com.example.blog.dto.user.*;
import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserProfileResponse me(String username) { return map(find(username)); }

    @Override
    public UserProfileResponse updateMyProfile(String username, UpdateProfileRequest request) {
        User user = find(username);
        user.setFullName(request.getFullName());
        return map(userRepository.save(user));
    }

    @Override
    public List<UserProfileResponse> listUsers() {
        return userRepository.findAll().stream().map(this::map).toList();
    }

    private User find(String username) { return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")); }

    private UserProfileResponse map(User u) {
        return UserProfileResponse.builder().id(u.getId()).username(u.getUsername()).email(u.getEmail()).fullName(u.getFullName())
                .role(u.getRoles().stream().findFirst().map(r -> r.getName().name()).orElse("ROLE_USER")).build();
    }
}
