package com.github.aayushjoshi2709.authservice.service;

import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
