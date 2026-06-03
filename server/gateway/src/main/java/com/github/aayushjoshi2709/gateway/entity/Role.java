package com.github.aayushjoshi2709.gateway.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table("Role")
@Getter
@Setter
@Builder
public class Role extends Common {
  @Size(max = 30)
  @Column("name")
  String name;

  @Size(max = 60)
  @Column("description")
  String description;
}
