package com.github.aayushjoshi2709.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.aayushjoshi2709.gateway.entity.Role;

interface RoleRepository extends JpaRepository<Role, Long> {
}
