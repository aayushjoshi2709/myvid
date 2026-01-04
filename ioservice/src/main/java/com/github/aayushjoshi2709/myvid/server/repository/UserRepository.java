package com.github.aayushjoshi2709.myvid.server.repository;

import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
