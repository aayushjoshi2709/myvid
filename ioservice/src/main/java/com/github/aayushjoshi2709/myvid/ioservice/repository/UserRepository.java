package com.github.aayushjoshi2709.myvid.ioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email, String username);
}
