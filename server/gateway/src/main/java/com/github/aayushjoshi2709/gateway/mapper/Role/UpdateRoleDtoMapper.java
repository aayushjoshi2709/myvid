package com.github.aayushjoshi2709.gateway.mapper.Role;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.gateway.dto.Roles.UpdateRoleDto;
import com.github.aayushjoshi2709.gateway.entity.Role;

@Mapper(componentModel = "spring")
public interface UpdateRoleDtoMapper {
  UpdateRoleDto toDto(Role r);

  Role toEntity(UpdateRoleDto urd);
}
