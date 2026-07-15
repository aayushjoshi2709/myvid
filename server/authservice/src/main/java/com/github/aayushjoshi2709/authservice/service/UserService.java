package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.common.PaginatedResponseDto;
import com.github.aayushjoshi2709.authservice.dto.user.*;
import com.github.aayushjoshi2709.authservice.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto create(CreateUserDto body);

    UserResponseDto findById(UUID id);

    User findUserById(UUID id);

    PaginatedResponseDto<List<UserResponseDto>> findAll(Integer page, Integer size);

    UserResponseDto update(UUID id, UpdateUserDto body);

    void delete(UUID id);

    LoginResponseDto login(LoginDto data);

    LoginResponseDto refresh(RefreshTokenRequestDto data);

    void Logout(RefreshTokenRequestDto data);
}
