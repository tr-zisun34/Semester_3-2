package com.example.UserManagement.application.interfaces;

import com.example.UserManagement.domain.Role;

import java.util.UUID;

public interface RoleRepository {
    void save(Role role);
}