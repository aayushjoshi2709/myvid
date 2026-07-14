package com.github.aayushjoshi2709.authservice.entity;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

import com.github.aayushjoshi2709.authservice.entity.enums.RoleStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="roles")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Common {
    @Column(name="name", nullable = false, length = 70)
    private String name;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private RoleStatusEnum status = RoleStatusEnum.ACTIVE;
}
