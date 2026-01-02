package com.github.aayushjoshi2709.myvid.server.mapper.user;

import com.github.aayushjoshi2709.myvid.server.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {
    CreateUserDto toDto(User user);
    User toEntity(CreateUserDto createUserDto);
}
