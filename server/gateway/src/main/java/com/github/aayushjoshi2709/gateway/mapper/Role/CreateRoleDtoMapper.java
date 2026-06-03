package com.github.aayushjoshi2709.gateway.mapper.Role;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.gateway.dto.Roles.CreateRoleDto;
import com.github.aayushjoshi2709.gateway.entity.Role;

@Mapper(componentModel = "spring")
public interface CreateRoleDtoMapper {
  CreateRoleDto toDto(Role r);

  Role toEntity(CreateRoleDto crd);
}
