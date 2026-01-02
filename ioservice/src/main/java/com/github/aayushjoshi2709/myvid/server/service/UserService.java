package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;

import java.util.UUID;

public interface UserService {
    GetUserDto getUser(UUID userId);
    GetUserDto registerUser(CreateUserDto userDetails);
    GetUserDto updateUser(UUID userId, CreateUserDto userDetails);
    void deleteUser(UUID userId);
}
