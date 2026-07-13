package com.github.aayushjoshi2709.authservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.authservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}
