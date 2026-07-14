package com.github.aayushjoshi2709.authservice.entity;
import java.util.List;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

import com.github.aayushjoshi2709.authservice.entity.enums.RoleStatusEnum;
import com.github.aayushjoshi2709.authservice.entity.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Common{
    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name="email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name="password", length = 60, nullable = false)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserStatusEnum status = UserStatusEnum.ACTIVE;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
