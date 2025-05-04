package com.example.UserManagement.infrastructure.controller;

import com.example.UserManagement.application.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<UUID> createRole(@Valid @RequestBody CreateRoleRequest request) {
        UUID roleId = roleService.createRole(request.getRoleName());
        return new ResponseEntity<>(roleId, HttpStatus.CREATED);
    }
}