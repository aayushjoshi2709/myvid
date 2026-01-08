package com.github.aayushjoshi2709.myvid.server.controller;

import com.github.aayushjoshi2709.myvid.server.dto.user.*;
import com.github.aayushjoshi2709.myvid.server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<GetUserDto> registerUser(@RequestBody @Valid CreateUserDto userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(userDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody @Valid LoginUserDto loginRequest) {
        return ResponseEntity.ok(this.userService.loginUser(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getCurrentUser() {
        return ResponseEntity.ok(this.userService.getCurrentUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDto> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.userService.getUser(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<GetUserDto> updateUser(@PathVariable UUID userId,
            @RequestBody @Valid UpdateUserDto updatedUserDetails) {
        return ResponseEntity.ok(this.userService.updateUser(userId, updatedUserDetails));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
