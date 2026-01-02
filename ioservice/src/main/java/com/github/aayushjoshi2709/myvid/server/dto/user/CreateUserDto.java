package com.github.aayushjoshi2709.myvid.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Long phoneNo;

    private String profilePicUrl;
}
