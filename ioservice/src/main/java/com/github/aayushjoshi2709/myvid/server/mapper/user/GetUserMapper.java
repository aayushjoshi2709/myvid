package com.github.aayushjoshi2709.myvid.server.mapper.user;

import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper {
    GetUserDto toDto(User user);
    User toEntity(GetUserDto getUserDto);
}
