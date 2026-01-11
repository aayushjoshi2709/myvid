package com.github.aayushjoshi2709.myvid.ioservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.*;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.enums.UserStatus;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.user.CreateUserMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.user.GetUserMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.user.UpdateUserMapper;
import com.github.aayushjoshi2709.myvid.ioservice.repository.UserRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.JwtService;
import com.github.aayushjoshi2709.myvid.ioservice.service.UserService;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GetUserMapper getUserMapper;
    private final CreateUserMapper createUserMapper;
    private final UpdateUserMapper updateUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User getUserById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found"));
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not authenticated");
        }
        return ((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername();
    }

    public User getCurrentUserDetails() {
        String username = getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public GetUserDto getUser(UUID userId) {
        User user = getUserById(userId);
        return this.getUserMapper.toDto(user);
    }

    @Override
    public GetUserDto getCurrentUser() {
        User user = this.getCurrentUserDetails();
        return this.getUserMapper.toDto(user);
    }

    @Override
    public GetUserDto registerUser(CreateUserDto userDetails) {
        if (userDetails.getPassword() != null) {
            userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        }

        User existingUser = this.userRepository.findByEmailOrUsername(userDetails.getEmail(),
                userDetails.getUsername()).orElse(null);

        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with same email or username already exist");
        }

        User user = createUserMapper.toEntity(userDetails);
        User createdUser = this.userRepository.save(user);
        return this.getUserMapper.toDto(createdUser);
    }

    @Override
    public GetUserDto updateUser(UUID userId, UpdateUserDto userDetails) {
        if (userDetails.getPassword() != null) {
            userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        }
        User user = getUserById(userId);
        this.updateUserMapper.updateUserFromDto(userDetails, user);
        User updatedUser = this.userRepository.save(user);
        return this.getUserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = getUserById(userId);
        user.setStatus(UserStatus.INACTIVE);
        this.userRepository.save(user);
    }

    @Override
    public LoginResponseDto loginUser(LoginUserDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }

        return new LoginResponseDto(jwtService.generateToken(loginRequest.getUsername()));
    }
}
