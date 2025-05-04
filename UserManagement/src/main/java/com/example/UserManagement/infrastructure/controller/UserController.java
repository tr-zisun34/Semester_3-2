package com.example.UserManagement.infrastructure.controller;

import com.example.UserManagement.application.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@Valid @RequestBody CreateUserRequest request) {
        UUID userId = userService.createUser(request.getName(), request.getEmail());
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
        userService.assignRoleToUser(userId, roleId);
        return new ResponseEntity<>("Role assigned successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable UUID id) {
        UserResponse response = UserResponse.fromDomain(userService.getUserDetails(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}