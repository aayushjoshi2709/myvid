package com.github.aayushjoshi2709.gateway.entity;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.relational.core.mapping.Column;

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
  @Size(max = 5)
  @Column("sourceVersion")
  String sourceVersion;

  @NotBlank
  @Size(max = 60)
  @Column("sourceEndpoint")
  String sourceEndpoint;

  @NotBlank
  @Size(max = 4)
  @Column("targetVersion")
  String targetVersion;

  @NotBlank
  @Size(max = 60)
  @Column("targetEndpoint")
  String targetEndpoint;

  @NotBlank
  @Column("roleId")
  Long roleId;
}
