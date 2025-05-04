package com.example.UserManagement.infrastructure.controller;

import com.example.UserManagement.domain.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private List<RoleResponse> roles;

    public UserResponse(UUID id, String name, String email, List<RoleResponse> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public static UserResponse fromDomain(User user) {
        List<RoleResponse> roleResponses = user.getRoles().stream()
                .map(role -> new RoleResponse(role.getId(), role.getRoleName()))
                .collect(Collectors.toList());
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), roleResponses);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<RoleResponse> getRoles() {
        return roles;
    }
}