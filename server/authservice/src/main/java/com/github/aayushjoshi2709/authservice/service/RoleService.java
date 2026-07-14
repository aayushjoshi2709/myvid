package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.role.CreateRoleDto;
import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.dto.role.UpdateRoleDto;
import com.github.aayushjoshi2709.authservice.entity.Role;
import com.github.aayushjoshi2709.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    public RoleResponseDto create(CreateRoleDto body);

    public RoleResponseDto findById(UUID id);

    public List<RoleResponseDto> findAll(Integer page, Integer size);

    public RoleResponseDto update(UUID id, UpdateRoleDto body);

    public void delete(UUID id);
}
