package com.example.UserManagement.infrastructure.controller;

import java.util.UUID;

public class RoleResponse {
    private UUID id;
    private String roleName;

    public RoleResponse(UUID id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UUID getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}