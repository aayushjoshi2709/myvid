package com.github.aayushjoshi2709.gateway.entity;

import com.github.aayushjoshi2709.gateway.entity.common.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Role extends Common {
    @Column(name="name", length = 20, nullable = false) 
    String name;
    
    @Column(name="description", length = 100, nullable = false)
    String description;
}
