package com.github.aayushjoshi2709.gateway.service;

import com.github.aayushjoshi2709.gateway.dto.Roles.CreateRoleDto;
import com.github.aayushjoshi2709.gateway.dto.Roles.UpdateRoleDto;
import com.github.aayushjoshi2709.gateway.entity.Role;
import com.github.aayushjoshi2709.gateway.mapper.Role.CreateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.mapper.Role.UpdateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public interface RoleService {
  public Flux<Role> find(Long limit, Long offset);

  public Mono<Role> findById(UUID id);

  public Mono<Role> create(@Valid CreateRoleDto body);

  public Mono<Role> update(UUID id, UpdateRoleDto body) ;

  public Mono<Void> delete(UUID id);
}
