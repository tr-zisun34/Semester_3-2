package com.example.UserManagement.application;

import com.example.UserManagement.domain.Role;
import com.example.UserManagement.domain.User;
import com.example.UserManagement.application.interfaces.UserRepository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(@NotBlank String name, @Email String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user.getId();
    }

    public void assignRoleToUser(UUID userId, UUID roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = userRepository.findRoleById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.assignRole(role);
        userRepository.save(user);
    }

    public User getUserDetails(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}