package com.example.UserManagement.application;

import com.example.UserManagement.application.interfaces.UserRepository;
import com.example.UserManagement.domain.Role;
import com.example.UserManagement.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_shouldSaveUserAndReturnId() {
        String name = "John Doe";
        String email = "john@example.com";

        UUID userId = userService.createUser(name, email);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(userId);
    }

    @Test
    void assignRoleToUser_shouldAssignRoleAndSaveUser() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        User user = new User("John Doe", "john@example.com");
        Role role = new Role("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findRoleById(roleId)).thenReturn(Optional.of(role));

        userService.assignRoleToUser(userId, roleId);

        verify(userRepository, times(1)).save(user);
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    void assignRoleToUser_shouldThrowExceptionIfUserNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userService.assignRoleToUser(userId, roleId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void getUserDetails_shouldReturnUser() {
        UUID userId = UUID.randomUUID();
        User user = new User("John Doe", "john@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserDetails(userId);

        assertEquals(user, result);
    }

    @Test
    void getUserDetails_shouldThrowExceptionIfUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userService.getUserDetails(userId));

        assertEquals("User not found", exception.getMessage());
    }
}