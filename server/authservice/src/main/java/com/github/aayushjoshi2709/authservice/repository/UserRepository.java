package com.github.aayushjoshi2709.authservice.repository;
import java.util.Optional;
import java.util.UUID;

import com.github.aayushjoshi2709.authservice.entity.enums.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.authservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByIdAndStatus(UUID id, UserStatusEnum userStatusEnum);

    Optional<User> findByUsernameAndStatus(String username, UserStatusEnum userStatusEnum);
}
