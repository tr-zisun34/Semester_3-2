package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.security.HashLib;

public class AuthManager {
    private final UserRepository userRepository;
    private final HashLib hashLib;

    public AuthManager(UserRepository userRepository, HashLib hashLib) {
        this.userRepository = userRepository;
        this.hashLib = hashLib;
    }

    public User UserFindByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    public boolean login(String email, String password) throws Exception {
        try {
            User user = UserFindByEmail(email);
            boolean passwordMatches = hashLib.hash(password, user.getPassword());
            if (passwordMatches) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("No user found");
        }
    }
}
