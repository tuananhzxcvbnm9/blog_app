package com.example.blogapp.repository;

import com.example.blogapp.entity.Role;
import com.example.blogapp.entity.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
