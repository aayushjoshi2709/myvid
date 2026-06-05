package com.github.aayushjoshi2709.gateway.controller;

import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.aayushjoshi2709.gateway.entity.Role;
import com.github.aayushjoshi2709.gateway.service.RoleService;
import com.github.aayushjoshi2709.gateway.dto.Roles.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  RoleController(
      final RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public Flux<ResponseEntity<Object>> getRoles(
      @Param("limit") Long limit,
      @Param("offset") Long offset) {
    return this.roleService.find(limit, offset).map(ResponseEntity::ok);
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Role>> getById(
      @Valid @PathVariable("id") @NotNull(message = "Please provide an id") @Positive(message = "The id should be a positive value") UUID id) {
    return this.roleService.findById(id).map(ResponseEntity::ok);
  }

  @PostMapping
  public Mono<ResponseEntity<Role>> createRole(@RequestBody @Valid CreateRoleDto body) {
    return this.roleService.create(body).map(response -> ResponseEntity.ok().body(response));
  }

  @PatchMapping("/{id}")
  public Mono<ResponseEntity<Role>> updateRole(
      @Valid @PathVariable("id") @NotNull(message = "Please provide an id") @Positive(message = "Id cannot be a negative value") UUID id,
      @Valid @RequestBody UpdateRoleDto body) {
    return this.roleService.update(id, body).map(ResponseEntity::ok);
  }

}
