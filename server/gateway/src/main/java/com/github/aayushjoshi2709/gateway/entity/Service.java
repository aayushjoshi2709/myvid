package com.github.aayushjoshi2709.gateway.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table("service")
@Getter
@Setter
@Builder
public class Service extends Common {
  @Column("serviceName")
  @NotBlank
  @Size(min = 2, max = 30)
  private String serviceName;

  @Column("serviceUrl")
  @NotBlank
  @Size(min = 2, max = 50)
  private String serviceUrl;
}
