package com.github.aayushjoshi2709.gateway.entity;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endpoint extends Common {
    @Column(name="serviceName", length = 20, nullable = false)
    String serviceName;

    @Column(name="sourceVersion", length = 3, nullable = false)
    String sourceVersion;

    @Column(name="sourceEndpoint",length = 100, nullable = false)
    String sourceEndpoint;

    @Column(name="targetVersion", length = 3, nullable = false)
    String targetVersion;

    @Column(name="targetEndpoint", length = 100, nullable = false)
    String targetEndpoint;
}
