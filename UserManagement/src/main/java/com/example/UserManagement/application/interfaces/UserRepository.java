package com.example.UserManagement.application.interfaces;

import com.example.UserManagement.domain.Role;
import com.example.UserManagement.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UUID id);
    Optional<Role> findRoleById(UUID roleId);
}