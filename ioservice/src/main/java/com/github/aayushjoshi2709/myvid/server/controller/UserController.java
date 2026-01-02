package com.github.aayushjoshi2709.myvid.server.controller;

import com.github.aayushjoshi2709.myvid.server.dto.user.CreateUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDto> getUser(@PathVariable UUID userId){
        return ResponseEntity.ok(this.userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<GetUserDto> registerUser(@RequestBody CreateUserDto userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(userDetails));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<GetUserDto> updateUser(@PathVariable UUID userId, @RequestBody CreateUserDto updatedUserDetails){
        return ResponseEntity.ok(this.userService.updateUser(userId, updatedUserDetails));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable  UUID userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
