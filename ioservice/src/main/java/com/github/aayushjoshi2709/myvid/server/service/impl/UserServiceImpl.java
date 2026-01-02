package com.github.aayushjoshi2709.myvid.server.service.impl;

import com.github.aayushjoshi2709.myvid.server.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import com.github.aayushjoshi2709.myvid.server.entity.enums.UserStatus;
import com.github.aayushjoshi2709.myvid.server.mapper.user.CreateUserMapper;
import com.github.aayushjoshi2709.myvid.server.mapper.user.GetUserMapper;
import com.github.aayushjoshi2709.myvid.server.mapper.user.UpdateUserMapper;
import com.github.aayushjoshi2709.myvid.server.repository.UserRepository;
import com.github.aayushjoshi2709.myvid.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GetUserMapper getUserMapper;
    private final CreateUserMapper createUserMapper;
    private final UpdateUserMapper updateUserMapper;


    private User getUserFromDb(UUID userId){
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found")
        );
    }

    @Override
    public GetUserDto getUser(UUID userId) {
        User user = getUserFromDb(userId);
        return this.getUserMapper.toDto(user);
    }

    @Override
    public GetUserDto registerUser(CreateUserDto userDetails) {
        User user = createUserMapper.toEntity(userDetails);
        User createdUser = this.userRepository.save(user);
        return this.getUserMapper.toDto(createdUser);
    }

    @Override
    public GetUserDto updateUser(UUID userId, UpdateUserDto userDetails) {
        User user = getUserFromDb(userId);
        this.updateUserMapper.updateUserFromDto(userDetails, user);
        User updatedUser = this.userRepository.save(user);
        return this.getUserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = getUserFromDb(userId);
        user.setStatus(UserStatus.INACTIVE);
        this.userRepository.save(user);
    }

}
