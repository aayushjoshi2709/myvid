package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.role.CreateRoleDto;
import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.dto.role.UpdateRoleDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    RoleResponseDto create(CreateRoleDto body);

    RoleResponseDto findById(UUID id);

    List<RoleResponseDto> findAll(Integer page, Integer size);

    RoleResponseDto update(UUID id, UpdateRoleDto body);

    void delete(UUID id);
}
