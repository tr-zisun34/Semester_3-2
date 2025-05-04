package com.example.UserManagement.config;

import com.example.UserManagement.application.RoleService;
import com.example.UserManagement.application.UserService;
import com.example.UserManagement.application.interfaces.RoleRepository;
import com.example.UserManagement.application.interfaces.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }
}