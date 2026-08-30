package com.github.aayushjoshi2709.authservice.mapper.role;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.authservice.dto.role.CreateRoleDto;
import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.entity.Role;

@Mapper(componentModel = "spring")
public interface CreateRoleMapper {
  Role toEntity(CreateRoleDto dto);

  RoleResponseDto toDto(Role role);
}
