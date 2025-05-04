package com.example.UserManagement.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    private UUID id;

    private String name;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleJpaEntity> roles = new ArrayList<>();

    public UserJpaEntity() {}

    public UserJpaEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<RoleJpaEntity> getRoles() {
        return roles;
    }

    public void addRole(RoleJpaEntity role) {
        roles.add(role);
    }
}