package com.example.blogapp.service.impl;

import com.example.blogapp.dto.common.PageResponse;
import com.example.blogapp.dto.user.UpdateProfileRequest;
import com.example.blogapp.dto.user.UserProfileResponse;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.service.UserService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserProfileResponse getMyProfile(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    @Override
    public UserProfileResponse updateMyProfile(String email, UpdateProfileRequest request) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFullName(request.fullName());
        user.setBio(request.bio());
        return toResponse(userRepository.save(user));
    }

    @Override
    public PageResponse<UserProfileResponse> listUsers(int page, int size) {
        var result = userRepository.findAll(PageRequest.of(page, size));
        return PageResponse.<UserProfileResponse>builder()
                .content(result.stream().map(this::toResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    private UserProfileResponse toResponse(com.example.blogapp.entity.User user) {
        return new UserProfileResponse(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getBio(),
                user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }
}
