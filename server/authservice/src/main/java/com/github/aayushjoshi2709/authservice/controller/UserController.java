package com.github.aayushjoshi2709.authservice.controller;

import com.github.aayushjoshi2709.authservice.dto.user.*;
import com.github.aayushjoshi2709.authservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PostMapping
    ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto body){
        return ResponseEntity.ok(this.userService.create(body));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto body){
        return ResponseEntity.ok(this.userService.login(body));
    }

    @PostMapping("/refresh")
    ResponseEntity<LoginResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto body){
        return ResponseEntity.ok(this.userService.refresh(body));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDto body){
        return ResponseEntity.ok(this.userService.update(id, body));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
