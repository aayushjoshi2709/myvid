package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.authservice.dto.user.UserResponseDto;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.entity.enums.UserStatusEnum;
import com.github.aayushjoshi2709.authservice.mapper.User.CreateUserMapper;
import com.github.aayushjoshi2709.authservice.mapper.User.UserResponseMapper;
import com.github.aayushjoshi2709.authservice.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
    private final UserResponseMapper userResponseMapper;
    private User findUserById(UUID id){
        return this.userRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User not found"
                )
        );
    }

    @Override
    public UserResponseDto create(CreateUserDto body) {
        User user = this.createUserMapper.toEntity(body);
        User savedUser = this.userRepository.save(user);
        return this.userResponseMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto findById(UUID id) {
        User user = this.findUserById(id);
        return this.userResponseMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll(Integer page, Integer size) {
        return this.userRepository.findAll().stream().map(userResponseMapper::toDto).toList();
    }

    @Override
    public UserResponseDto update(UUID id, UpdateUserDto body) {
        User user = this.findUserById(id);
        String name = body.name(), username = body.username(), email = body.email();
        if(!name.isEmpty()){
            user.setName(name);
        }

        if(!username.isEmpty()){
            user.setUsername(username);
        }

        if(!email.isEmpty()){
            user.setEmail(email);
        }

        try {
            return this.userResponseMapper.toDto(this.userRepository.save(user));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email already exists");
        }
    }

    @Override
    public void delete(UUID id) {
        User user = this.findUserById(id);
        user.setStatus(UserStatusEnum.IN_ACTIVE);
        this.userRepository.save(user);
    }
}
