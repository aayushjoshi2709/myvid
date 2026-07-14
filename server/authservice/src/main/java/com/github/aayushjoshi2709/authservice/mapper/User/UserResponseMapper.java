package com.github.aayushjoshi2709.authservice.mapper.User;

import com.github.aayushjoshi2709.authservice.dto.user.UserResponseDto;
import com.github.aayushjoshi2709.authservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userResponseDto);
}
