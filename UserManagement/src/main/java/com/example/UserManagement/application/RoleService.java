package com.example.UserManagement.application;

import com.example.UserManagement.domain.Role;
import com.example.UserManagement.application.interfaces.RoleRepository;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UUID createRole(@NotBlank String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
        return role.getId();
    }
}