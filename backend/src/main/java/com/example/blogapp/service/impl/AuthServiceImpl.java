package com.example.blogapp.service.impl;

import com.example.blogapp.dto.auth.AuthResponse;
import com.example.blogapp.dto.auth.LoginRequest;
import com.example.blogapp.dto.auth.RegisterRequest;
import com.example.blogapp.entity.RoleName;
import com.example.blogapp.entity.User;
import com.example.blogapp.exception.BadRequestException;
import com.example.blogapp.repository.RoleRepository;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.security.JwtService;
import com.example.blogapp.service.AuthService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email()) || userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("Email hoặc username đã tồn tại");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .build();

        user.getRoles().add(roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new BadRequestException("Default role not found")));

        var saved = userRepository.save(user);
        var token = jwtService.generateToken(org.springframework.security.core.userdetails.User.withUsername(saved.getEmail())
                .password(saved.getPassword())
                .authorities(saved.getRoles().stream().map(r -> r.getName().name()).toArray(String[]::new)).build());

        return new AuthResponse(token, "Bearer", saved.getId(), saved.getEmail(), saved.getUsername(),
                saved.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email()).orElseThrow(() -> new BadRequestException("Invalid credentials"));
        var token = jwtService.generateToken(org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword()).authorities(user.getRoles().stream().map(r -> r.getName().name()).toArray(String[]::new)).build());
        Set<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet());
        return new AuthResponse(token, "Bearer", user.getId(), user.getEmail(), user.getUsername(), roles);
    }
}
