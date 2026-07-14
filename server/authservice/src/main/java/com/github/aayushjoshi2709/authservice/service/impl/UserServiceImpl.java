package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UserResponseDto;
import com.github.aayushjoshi2709.authservice.service.UserService;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto create(CreateUserDto body) {
        return null;
    }

    @Override
    public UserResponseDto findById(UUID id) {
        return null;
    }

    @Override
    public List<UserResponseDto> findAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public UserResponseDto update(UUID id, UpdateUserDto body) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
