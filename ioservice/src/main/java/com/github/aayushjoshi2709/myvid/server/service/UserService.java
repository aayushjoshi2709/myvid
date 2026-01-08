package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.user.*;
import com.github.aayushjoshi2709.myvid.server.entity.User;

import java.util.UUID;

public interface UserService {
    GetUserDto getUser(UUID userId);

    GetUserDto registerUser(CreateUserDto userDetails);

    GetUserDto updateUser(UUID userId, UpdateUserDto userDetails);

    void deleteUser(UUID userId);

    LoginResponseDto loginUser(LoginUserDto loginRequest);

    GetUserDto getCurrentUser();

    User getCurrentUserDetails();
}
