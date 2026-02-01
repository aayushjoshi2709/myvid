package com.github.aayushjoshi2709.myvid.ioservice.mapper.user;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

@Mapper(componentModel = "spring")
public interface GetUserMapper {
    GetUserDto toDto(User user);
    User toEntity(GetUserDto getUserDto);
}
