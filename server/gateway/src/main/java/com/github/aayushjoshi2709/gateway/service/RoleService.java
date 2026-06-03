package com.github.aayushjoshi2709.gateway.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.dto.Roles.UpdateRoleDto;
import com.github.aayushjoshi2709.gateway.entity.Role;
import com.github.aayushjoshi2709.gateway.mapper.Role.CreateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.mapper.Role.UpdateRoleDtoMapper;
import com.github.aayushjoshi2709.gateway.repository.RoleRepository;
import com.github.aayushjoshi2709.gateway.dto.Roles.CreateRoleDto;
import jakarta.validation.Valid;

import java.util.List;

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

  public List<Role> find(Long limit, Long offset) {
    return this.roleRepository.findAll();
  }

  public Role findById(Long id) throws ResponseStatusException {
    return this.roleRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No role present with the given id: " + id));
  }

  public Role create(@Valid CreateRoleDto body) {
    Role role = this.createRoleDtoMapper.toEntity(body);
    return this.roleRepository.save(role);
  }

  public Role update(Long id, UpdateRoleDto body) {
    Role role = this.findById(id);
    String name = body.name();
    String desc = body.description();
    if (name.length() == 0) {
      role.setName(name);
    }
    if (desc.length() == 0) {
      role.setName(desc);
    }
    return this.roleRepository.save(role);
  }

  public void delete(Long id) {
    this.roleRepository.deleteById(id);
  }
}
