package com.github.aayushjoshi2709.authservice.dto.role;

import java.util.Objects;

public record CreateRoleDto(String name, String description) {
  public CreateRoleDto(String name, String description) {
    Objects.requireNonNull(name, "name cannot be null");
    this.name = name.toUpperCase();
    this.description = description;
  }
}
