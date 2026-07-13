package com.github.aayushjoshi2709.authservice.controller;

import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aayushjoshi2709.authservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    ResponseEntity<RoleResponseDto> createRole(){

    }

}
