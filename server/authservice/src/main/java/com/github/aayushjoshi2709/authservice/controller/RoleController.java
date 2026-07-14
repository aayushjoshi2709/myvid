package com.github.aayushjoshi2709.authservice.controller;

import com.github.aayushjoshi2709.authservice.dto.role.CreateRoleDto;
import com.github.aayushjoshi2709.authservice.dto.role.RoleResponseDto;
import com.github.aayushjoshi2709.authservice.dto.role.UpdateRoleDto;
import com.github.aayushjoshi2709.authservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    ResponseEntity<RoleResponseDto> createRole(@RequestBody CreateRoleDto body){
        return ResponseEntity.accepted().body(this.roleService.create(body));
    }

    @GetMapping("/{id}")
    ResponseEntity<RoleResponseDto> getRole(@PathVariable UUID id){
        return ResponseEntity.ok(this.roleService.findById(id));
    }

    @GetMapping("")
    ResponseEntity<List<RoleResponseDto>> getRoles(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.ok(this.roleService.findAll(page, size));
    }

    @PutMapping("/{id}")
    ResponseEntity<RoleResponseDto> updateRole(@PathVariable UUID id, UpdateRoleDto body){
        return ResponseEntity.ok(this.roleService.update(id, body));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRole(@PathVariable UUID id){
        this.roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
