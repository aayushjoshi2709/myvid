package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public UserResponseDto create(CreateUserDto body);

    public UserResponseDto findById(UUID id);

    public List<UserResponseDto> findAll(Integer page, Integer size);

    public UserResponseDto update(UUID id, UpdateUserDto body);

    public void delete(UUID id);
}
