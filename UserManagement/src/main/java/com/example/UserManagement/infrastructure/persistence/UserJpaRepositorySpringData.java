package com.example.UserManagement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserJpaRepositorySpringData extends JpaRepository<UserJpaEntity, UUID> {
}