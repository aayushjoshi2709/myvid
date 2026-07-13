package com.github.aayushjoshi2709.authservice.entity;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role extends Common {
    private String name;
    private String description;
}
