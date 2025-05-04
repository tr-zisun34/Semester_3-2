package com.example.UserManagement.infrastructure.persistence;

import com.example.UserManagement.application.interfaces.UserRepository;
import com.example.UserManagement.domain.Role;
import com.example.UserManagement.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserJpaRepository implements UserRepository {
    private final UserJpaRepositorySpringData userJpaRepositorySpringData;
    private final RoleJpaRepositorySpringData roleJpaRepositorySpringData;

    public UserJpaRepository(UserJpaRepositorySpringData userJpaRepositorySpringData,
                             RoleJpaRepositorySpringData roleJpaRepositorySpringData) {
        this.userJpaRepositorySpringData = userJpaRepositorySpringData;
        this.roleJpaRepositorySpringData = roleJpaRepositorySpringData;
    }

    @Override
    public void save(User user) {
        UserJpaEntity entity = new UserJpaEntity(user.getId(), user.getName(), user.getEmail());
        user.getRoles().forEach(role -> {
            RoleJpaEntity roleEntity = roleJpaRepositorySpringData.findById(role.getId())
                    .orElse(new RoleJpaEntity(role.getId(), role.getRoleName()));
            entity.addRole(roleEntity);
        });
        userJpaRepositorySpringData.save(entity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepositorySpringData.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Role> findRoleById(UUID roleId) {
        return roleJpaRepositorySpringData.findById(roleId)
                .map(roleEntity -> new Role(roleEntity.getRoleName()));
    }

    private User toDomain(UserJpaEntity entity) {
        User user = new User(entity.getName(), entity.getEmail());
        entity.getRoles().forEach(roleEntity -> user.assignRole(new Role(roleEntity.getRoleName())));
        return user;
    }
}