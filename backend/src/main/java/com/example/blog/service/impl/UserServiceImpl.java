package com.example.blog.service.impl;

import com.example.blog.dto.user.UserProfileResponse;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserProfileResponse me(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        return UserProfileResponse.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).fullName(user.getFullName()).build();
    }
}
