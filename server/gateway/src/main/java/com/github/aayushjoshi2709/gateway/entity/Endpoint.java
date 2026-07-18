package com.github.aayushjoshi2709.gateway.entity;

import com.github.aayushjoshi2709.gateway.entity.common.Common;
import com.github.aayushjoshi2709.gateway.entity.enums.HttpMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.relational.core.mapping.Column;

import java.util.List;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table("endpoint")
public class Endpoint extends Common {
  @NotBlank
  @Column("serviceId")
  UUID serviceId;

  @NotBlank
  @Size(max = 150)
  @Column("endpoint")
  String endpoint;

  @NotNull
  @Column("method")
  HttpMethod method;

  @Column("roles")
  List<String> roles;
}
