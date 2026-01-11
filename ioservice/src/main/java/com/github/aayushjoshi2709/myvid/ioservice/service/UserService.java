package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.*;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

public interface UserService {
    GetUserDto getUser(UUID userId);

    GetUserDto registerUser(CreateUserDto userDetails);

    GetUserDto updateUser(UUID userId, UpdateUserDto userDetails);

    void deleteUser(UUID userId);

    LoginResponseDto loginUser(LoginUserDto loginRequest);

    GetUserDto getCurrentUser();

    User getUserById(UUID userId);

    User getCurrentUserDetails();
}
