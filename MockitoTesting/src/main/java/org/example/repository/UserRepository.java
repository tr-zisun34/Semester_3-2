package org.example.repository;

import org.example.model.User;

public interface UserRepository {
    User findByEmail(String email) throws Exception;
}