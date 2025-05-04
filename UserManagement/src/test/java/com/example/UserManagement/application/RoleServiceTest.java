package com.example.UserManagement.application;

import com.example.UserManagement.application.interfaces.RoleRepository;
import com.example.UserManagement.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceTest {
    private RoleService roleService;
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void createRole_shouldSaveRoleAndReturnId() {
        String roleName = "ADMIN";

        UUID roleId = roleService.createRole(roleName);

        verify(roleRepository, times(1)).save(any(Role.class));
        assertNotNull(roleId);
    }
}