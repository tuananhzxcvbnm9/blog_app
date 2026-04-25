package com.example.blogapp.service;

import com.example.blogapp.dto.user.UpdateProfileRequest;
import com.example.blogapp.dto.user.UserProfileResponse;
import com.example.blogapp.dto.common.PageResponse;

public interface UserService {
    UserProfileResponse getMyProfile(String email);
    UserProfileResponse updateMyProfile(String email, UpdateProfileRequest request);
    PageResponse<UserProfileResponse> listUsers(int page, int size);
}
