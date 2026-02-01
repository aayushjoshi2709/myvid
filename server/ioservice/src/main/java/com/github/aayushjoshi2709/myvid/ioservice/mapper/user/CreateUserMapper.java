package com.github.aayushjoshi2709.myvid.ioservice.mapper.user;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {
    CreateUserDto toDto(User user);

    User toEntity(CreateUserDto createUserDto);
}
