package com.github.aayushjoshi2709.authservice.entity;
import java.util.List;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

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
    private String name;
    private String username;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
