package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.common.PaginatedResponseDto;
import com.github.aayushjoshi2709.authservice.dto.user.*;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.Role;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.entity.enums.UserStatusEnum;
import com.github.aayushjoshi2709.authservice.mapper.user.CreateUserMapper;
import com.github.aayushjoshi2709.authservice.mapper.user.UserResponseMapper;
import com.github.aayushjoshi2709.authservice.service.JwtService;
import com.github.aayushjoshi2709.authservice.service.RefreshTokenService;
import com.github.aayushjoshi2709.authservice.service.RoleService;
import com.github.aayushjoshi2709.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final CreateUserMapper createUserMapper;
  private final UserResponseMapper userResponseMapper;
  private final PasswordEncoder passwordEncoder;
  private final RefreshTokenService refreshTokenService;
  private final JwtService jwtService;
  private final RoleService roleService;

  private String getEncryptedPassword(String password) {
    return this.passwordEncoder.encode(password);
  }

  private Boolean matchPassword(String providedPassword, String hashedPassword) {
    return this.passwordEncoder.matches(providedPassword, hashedPassword);
  }

  public User findUserById(UUID id) {
    return this.userRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE).orElseThrow(
        () -> new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "User not found"));
  }

  @Override
  public LoginResponseDto login(LoginDto data) {
    User user = this.userRepository.findByUsernameAndStatus(data.username(), UserStatusEnum.ACTIVE).orElseThrow(
        () -> new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "User not found"));

    if (!matchPassword(data.password(), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
    String accessToken = this.jwtService.generateNewAccessToken(user);
    RefreshToken refreshToken = this.refreshTokenService.generateNewRefreshToken(user.getId(), accessToken);
    return new LoginResponseDto(
        refreshToken.getCurrentAccessToken(),
        refreshToken.getRefreshToken());
  }

  @Override
  public LoginResponseDto refresh(RefreshTokenRequestDto data) {
    RefreshToken refreshToken = this.refreshTokenService.findByRefreshToken(data.token());
    User user = this.findUserById(refreshToken.getUserId());
    String accessToken = this.jwtService.generateNewAccessToken(user);
    RefreshToken updatedRefreshToken = this.refreshTokenService.updateAccessToken(refreshToken, accessToken);
    return new LoginResponseDto(
        updatedRefreshToken.getCurrentAccessToken(),
        updatedRefreshToken.getRefreshToken());
  }

  @Override
  public void Logout(RefreshTokenRequestDto data) {
    this.refreshTokenService.revokeRefreshToken(data.token());
  }

  @Override
  public UserResponseDto create(CreateUserDto body) throws ResponseStatusException {
    User user = this.createUserMapper.toEntity(body);

    Optional<User> existingUser = this.userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

    log.debug("Creating new user {}", user.getUsername());

    if (existingUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
    }

    user.setPassword(this.getEncryptedPassword(user.getPassword()));
    user.setStatus(UserStatusEnum.ACTIVE);


    List<Role> defaultRoles = new ArrayList<>();

    Role userRole = this.roleService.findByName("USER");
    defaultRoles.add(userRole);
    user.setRoles(defaultRoles);

    User savedUser = this.userRepository.save(user);
    log.debug("Created user {}", savedUser.getId());
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

    Long phoneNo = body.phoneNo();
    String firstName = body.firstName(), lastName=body.lastName(), username = body.username(), email = body.email(), profilePicUrl = body.profilePicUrl();
    if (!firstName.isEmpty()) {
      user.setFirstName(firstName);
    }

    if (!lastName.isEmpty()) {
      user.setLastName(lastName);
    }

    if(phoneNo != null){
      user.setPhoneNo(phoneNo);
    }

    if (!username.isEmpty()) {
      user.setUsername(username);
    }

    if (!email.isEmpty()) {
      user.setEmail(email);
    }

    if (!profilePicUrl.isEmpty()) {
      user.setProfilePicUrl(profilePicUrl);
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
    this.refreshTokenService.revokeRefreshTokenByUser(user.getId());
  }
}
