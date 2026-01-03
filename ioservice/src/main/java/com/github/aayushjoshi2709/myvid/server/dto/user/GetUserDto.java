package com.github.aayushjoshi2709.myvid.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Long phoneNo;
    private String profilePicUrl;
}