package com.github.aayushjoshi2709.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.github.aayushjoshi2709.gateway.entity.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {
}
