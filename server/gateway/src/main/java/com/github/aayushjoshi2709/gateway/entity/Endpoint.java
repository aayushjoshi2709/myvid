package com.github.aayushjoshi2709.gateway.entity;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "endpoint", uniqueConstraints = {
    @UniqueConstraint(name = "uk_serviceName_sourceVersion_sourceEndpoint", columnNames = { "serviceName",
        "sourceVersion", "sourceEndpoint" })
}, indexes = {
    @Index(name = "idx_serviceName", columnList = "serviceName")
})
public class Endpoint extends Common {
  @Column(name = "serviceName", length = 20, nullable = false)
  String serviceName;

  @Column(name = "sourceVersion", length = 3, nullable = false)
  String sourceVersion;

  @Column(name = "sourceEndpoint", length = 100, nullable = false)
  String sourceEndpoint;

  @Column(name = "targetVersion", length = 3, nullable = false)
  String targetVersion;

  @Column(name = "targetEndpoint", length = 100, nullable = false)
  String targetEndpoint;

  @OneToMany(mappedBy = "endpoint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  List<Role> roles;
}
