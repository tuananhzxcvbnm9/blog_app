package com.example.blog.service;

import com.example.blog.dto.user.UserProfileResponse;

public interface UserService {
    UserProfileResponse me(String email);
}
