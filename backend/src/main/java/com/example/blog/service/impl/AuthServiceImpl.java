package com.example.blog.service.impl;

import com.example.blog.dto.auth.*;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.entity.enums.RoleName;
import com.example.blog.exception.BadRequestException;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.security.JwtService;
import com.example.blog.security.UserPrincipal;
import com.example.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Username hoặc email đã tồn tại");
        }
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new BadRequestException("Role USER chưa được seed"));
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .roles(Set.of(userRole))
                .build();
        userRepository.save(user);
        UserPrincipal principal = new UserPrincipal(user);
        return AuthResponse.builder().accessToken(jwtService.generateToken(principal)).tokenType("Bearer")
                .username(user.getUsername()).role("ROLE_USER").build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        UserPrincipal principal = new UserPrincipal(user);
        return AuthResponse.builder().accessToken(jwtService.generateToken(principal)).tokenType("Bearer")
                .username(user.getUsername()).role(principal.roles().getFirst()).build();
    }
}
