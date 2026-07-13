package com.github.aayushjoshi2709.authservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.authservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> { }
