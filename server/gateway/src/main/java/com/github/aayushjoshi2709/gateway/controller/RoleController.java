package com.github.aayushjoshi2709.gateway.controller;

import java.util.List;

import org.hibernate.persister.collection.mutation.UpdateRowsCoordinatorHistory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.aayushjoshi2709.gateway.entity.Role;
import com.github.aayushjoshi2709.gateway.service.RoleService;
import com.github.aayushjoshi2709.gateway.dto.Roles.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController("/role")
@Validated
public class RoleController {

  private final RoleService roleService;

  RoleController(
      final RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<List<Role>> getRoles(
      @Param("limit") Long limit,
      @Param("offset") Long offset) {
    return ResponseEntity.ok(this.roleService.find(limit, offset));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Role> getById(
      @Valid @PathVariable("id") @NotNull(message = "Please provide an id") @Positive(message = "The id should be a positive value") Long id) {
    return ResponseEntity.ok(this.roleService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Role> createRole(@RequestBody @Valid CreateRoleDto body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.create(body));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Role> updateRole(
      @Valid @PathVariable("id") @NotNull(message = "Please provide an id") @Positive(message = "Id cannot be a negative value") Long id,
      @Valid @RequestBody UpdateRoleDto body) {
    return ResponseEntity.ok(this.roleService.update(id, body));
  }

}
