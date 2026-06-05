package com.github.aayushjoshi2709.gateway.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.dto.Roles.UpdateRoleDto;
import com.github.aayushjoshi2709.gateway.entity.Role;
import com.github.aayushjoshi2709.gateway.mapper.Role.CreateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.mapper.Role.UpdateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.repository.RoleRepository;
import com.github.aayushjoshi2709.gateway.dto.Roles.CreateRoleDto;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoleService {
  private final RoleRepository roleRepository;
  private final CreateRoleDtoMapper createRoleDtoMapper;

  RoleService(
      final RoleRepository roleRepository,
      final UpdateRoleDtoMapper updateRoleDtoMapper,
      final CreateRoleDtoMapper createRoleDtoMapper) {
    this.roleRepository = roleRepository;
    this.createRoleDtoMapper = createRoleDtoMapper;
  }

  public Flux<Role> find(Long limit, Long offset) {
    return this.roleRepository.findAll();
  }

  public Mono<Role> findById(UUID id) throws ResponseStatusException {
    return this.roleRepository.findById(id).switchIfEmpty(
        Mono.error(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No role present with the given id: " + id)));
  }

  public Mono<Role> create(@Valid CreateRoleDto body) {
    Role role = this.createRoleDtoMapper.toEntity(body);
    return this.roleRepository.save(role);
  }

  public Mono<Role> update(UUID id, UpdateRoleDto body) {
    return this.findById(id).flatMap(role -> {
      String name = body.name();
      String desc = body.description();
      if (name.length() == 0) {
        role.setName(name);
      }
      if (desc.length() == 0) {
        role.setName(desc);
      }
      return this.roleRepository.save(role);
    });
  }

  public Mono<Void> delete(UUID id) {
    return this.roleRepository.deleteById(id).then();
  }
}
