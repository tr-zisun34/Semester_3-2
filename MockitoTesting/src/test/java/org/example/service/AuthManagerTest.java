package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.security.HashLib;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthManagerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HashLib hashLib;

    private AuthManager authManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authManager = new AuthManager(userRepository, hashLib);
    }

    @Test
    public void testLogin_NoUserFound_ShouldThrowException() throws Exception {
        String email = "nonexistent@example.com";
        String password = "anyPassword";

        when(userRepository.findByEmail(email)).thenThrow(new Exception("User not found"));

        Exception exception = assertThrows(Exception.class, () -> {
            authManager.login(email, password);
        });

        assertEquals("No user found", exception.getMessage());
    }

    @Test
    public void testLogin_SuccessfulLogin() throws Exception {
        String email = "user@example.com";
        String plainPassword = "123456";
        String hashedPassword = "hashed_123456";

        User mockUser = new User(email, hashedPassword);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(hashLib.hash(plainPassword, hashedPassword)).thenReturn(true);

        boolean result = authManager.login(email, plainPassword);

        assertTrue(result);
    }

    @Test
    public void testLogin_WrongPassword() throws Exception {
        String email = "user@example.com";
        String plainPassword = "wrongPassword";
        String hashedPassword = "hashed_correctPassword";

        User mockUser = new User(email, hashedPassword);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(hashLib.hash(plainPassword, hashedPassword)).thenReturn(false);

        boolean result = authManager.login(email, plainPassword);

        assertFalse(result);
    }

    @Test
    public void testHash_MatchingStrings_ShouldReturnTrue() {
        HashLib realHashLib = new HashLib();
        boolean result = realHashLib.hash("abc123", "abc123");
        assertTrue(result);
    }

    @Test
    public void testHash_DifferentStrings_ShouldReturnFalse() {
        HashLib realHashLib = new HashLib();
        boolean result = realHashLib.hash("abc123", "wrong");
        assertFalse(result);
    }

    @Test
    public void testHash_NullPlain_ShouldReturnFalse() {
        HashLib realHashLib = new HashLib();
        boolean result = realHashLib.hash(null, "abc123");
        assertFalse(result);
    }

    @Test
    public void testHash_NullHashed_ShouldReturnFalse() {
        HashLib realHashLib = new HashLib();
        boolean result = realHashLib.hash("abc123", null);
        assertFalse(result);
    }

    @Test
    public void testHash_BothNull_ShouldReturnFalse() {
        HashLib realHashLib = new HashLib();
        boolean result = realHashLib.hash(null, null);
        assertFalse(result);
    }

}