package com.github.aayushjoshi2709.authservice.mapper.role;

import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleResponseMapper {
  RoleResponseDto toDto(Role role);

  Role toEntity(RoleResponseDto roleResponseDto);
}
