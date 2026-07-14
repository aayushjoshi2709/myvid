package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.common.PaginatedResponseDto;
import com.github.aayushjoshi2709.authservice.dto.user.*;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.entity.enums.UserStatusEnum;
import com.github.aayushjoshi2709.authservice.mapper.User.CreateUserMapper;
import com.github.aayushjoshi2709.authservice.mapper.User.UserResponseMapper;
import com.github.aayushjoshi2709.authservice.repository.RefreshTokenRepository;
import com.github.aayushjoshi2709.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CreateUserMapper createUserMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${appdata.defaults.accessTokenExpiryDays}")
    private Integer accessTokenExpiryDays;

    private String getEncryptedPassword(String password){
        return this.passwordEncoder.encode(password);
    }

    private Boolean matchPassword(String providedPassword, String hashedPassword){
        return this.passwordEncoder.matches(providedPassword, hashedPassword);
    }


    private User findUserById(UUID id){
        return this.userRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User not found"
                )
        );
    }

    private String getNewRefreshToken(User user){
        StringKeyGenerator generator = new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 22);
        RefreshToken refreshToken = new RefreshToken(user.getId(), generator.generateKey());
        return this.refreshTokenRepository.save(refreshToken).getToken();
    }

    private String generateJWTToken(User user){
        return "";
    }


    @Override
    public LoginResponseDto login(LoginDto data){
        User user = this.userRepository.findByUsernameAndStatus(data.username(), UserStatusEnum.ACTIVE).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User not found"
                )
        );

        if(!matchPassword(data.password(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        return new LoginResponseDto(generateJWTToken(user), getNewRefreshToken(user));
    }

    @Override
    public LoginResponseDto refresh(RefreshTokenRequestDto data) {
        return null;
    }

    @Override
    public void Logout(RefreshTokenRequestDto data) {

    }

    @Override
    public UserResponseDto create(CreateUserDto body) {
        User user = this.createUserMapper.toEntity(body);
        user.setPassword(this.getEncryptedPassword(user.getPassword()));
        User savedUser = this.userRepository.save(user);
        return this.userResponseMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto findById(UUID id) {
        User user = this.findUserById(id);
        return this.userResponseMapper.toDto(user);
    }

    @Override
    public PaginatedResponseDto<List<UserResponseDto>> findAll(Integer page, Integer limit) {
        List<UserResponseDto> users = this.userRepository.findAll().stream().map(userResponseMapper::toDto).toList();
        return new PaginatedResponseDto<>(page, limit, 0, users);
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
