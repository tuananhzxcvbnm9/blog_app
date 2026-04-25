package com.example.blog.service;

import com.example.blog.dto.user.UpdateProfileRequest;
import com.example.blog.dto.user.UserProfileResponse;

import java.util.List;

public interface UserService {
    UserProfileResponse me(String username);
    UserProfileResponse updateMyProfile(String username, UpdateProfileRequest request);
    List<UserProfileResponse> listUsers();
}
