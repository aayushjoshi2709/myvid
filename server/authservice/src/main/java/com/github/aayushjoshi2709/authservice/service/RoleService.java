package com.github.aayushjoshi2709.authservice.service;

import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.authservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
