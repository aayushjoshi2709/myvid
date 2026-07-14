package com.github.aayushjoshi2709.authservice.mapper.User;

import com.github.aayushjoshi2709.authservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.authservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {
    User toEntity(CreateUserDto dto);

    CreateUserDto toDto(User role);
}