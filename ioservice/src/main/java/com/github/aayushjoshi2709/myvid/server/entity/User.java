package com.github.aayushjoshi2709.myvid.server.entity;

import com.github.aayushjoshi2709.myvid.server.entity.Common.Common;
import com.github.aayushjoshi2709.myvid.server.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User extends Common {
    @Column(name="username", length = 25, nullable = false, unique = true)
    private String username;

    @Column(name="password", length = 35, nullable = false)
    private String password;

    @Column(name="email", length = 35, nullable = false, unique = true)
    private String email;

    @Column(name="firstName", length = 25, nullable = false)
    private String firstName;

    @Column(name="lastName", length = 25, nullable = false)
    private String lastName;

    @Column(name="phoneNumber", length = 25, nullable = false)
    private Long phoneNo;

    @Column(name="profilePicUrl", length = 50)
    private String profilePicUrl;

    private UserStatus status = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "createdBy")
    private List<Video> videos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_subscriptions",
            joinColumns = @JoinColumn(name="subscriber_id"),
            inverseJoinColumns = @JoinColumn(name="subscribed_to_id"),

    )
    private Set<User> subscribedTo = new HashSet<>();

    @ManyToMany(mappedBy = "subscribedTo", fetch = FetchType.LAZY)
    private Set<User> subscribers = new HashSet<>();
}
