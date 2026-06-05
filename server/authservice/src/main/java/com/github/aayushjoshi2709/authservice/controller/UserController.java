package com.github.aayushjoshi2709.authservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.aayushjoshi2709.authservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
