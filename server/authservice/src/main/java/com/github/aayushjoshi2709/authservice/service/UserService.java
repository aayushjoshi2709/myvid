package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.LoginDto;
import com.github.aayushjoshi2709.authservice.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto create(CreateUserDto body);

    UserResponseDto findById(UUID id);

    List<UserResponseDto> findAll(Integer page, Integer size);

    UserResponseDto update(UUID id, UpdateUserDto body);

    void delete(UUID id);

    LoginResponseDto login(LoginDto data);
}
