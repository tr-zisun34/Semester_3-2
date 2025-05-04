package com.example.UserManagement.infrastructure.persistence;

import com.example.UserManagement.application.interfaces.RoleRepository;
import com.example.UserManagement.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleJpaRepository implements RoleRepository {
    private final RoleJpaRepositorySpringData roleJpaRepositorySpringData;

    public RoleJpaRepository(RoleJpaRepositorySpringData roleJpaRepositorySpringData) {
        this.roleJpaRepositorySpringData = roleJpaRepositorySpringData;
    }

    @Override
    public void save(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity(role.getId(), role.getRoleName());
        roleJpaRepositorySpringData.save(entity);
    }
}