package com.example.UserManagement.infrastructure.controller;

import jakarta.validation.constraints.NotBlank;

public class CreateRoleRequest {
    @NotBlank
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}