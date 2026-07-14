package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.role.CreateRoleDto;
import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.dto.role.UpdateRoleDto;
import com.github.aayushjoshi2709.authservice.entity.Role;
import com.github.aayushjoshi2709.authservice.entity.enums.RoleStatusEnum;
import com.github.aayushjoshi2709.authservice.mapper.Role.CreateRoleMapper;
import com.github.aayushjoshi2709.authservice.mapper.Role.RoleResponseMapper;
import com.github.aayushjoshi2709.authservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final CreateRoleMapper createRoleMapper;
    private final RoleResponseMapper roleResponseMapper;

    private Role findRoleById(UUID id){
        return this.roleRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Role not found"
                )
        );
    }

    public RoleResponseDto create(CreateRoleDto body) {
        Role savedRole = this.roleRepository.save(createRoleMapper.toEntity(body));
        return roleResponseMapper.toDto(savedRole);
    }

    public RoleResponseDto findById(UUID id) {
        Role role = findRoleById(id);
        return roleResponseMapper.toDto(role);
    }

    public List<RoleResponseDto> findAll(Integer page, Integer size){
        return this.roleRepository.findAll().stream().map(roleResponseMapper::toDto).toList();
    }

    public RoleResponseDto update(UUID id, UpdateRoleDto body){
        Role role = findRoleById(id);
        String name = body.name(), description = body.description();

        if(!name.isEmpty()) {
            role.setName(name);
        }

        if(!description.isEmpty()) {
            role.setDescription(description);
        }
        return roleResponseMapper.toDto(this.roleRepository.save(role));
    }

    public void delete(UUID id) {
        Role role = findRoleById(id);
        role.setStatus(RoleStatusEnum.IN_ACTIVE);
        this.roleRepository.save(role);
    }
}
