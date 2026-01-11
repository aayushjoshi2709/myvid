package com.github.aayushjoshi2709.myvid.ioservice.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 25, message = "Username must be between 2 to 25 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 35, message = "Password must be between 8 and 35 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 35, message = "Email can be max 35 characters")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(max = 25, message = "First name can be max 25 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 25, message = "Last name can be max 25 characters")
    private String lastName;

    @NotNull(message = "Phone number is required")
    @Digits(integer = 15, fraction = 0, message = "Invalid phone number")
    private Long phoneNo;

    @Size(max = 150, message = "Profile pic URL can be max 50 characters")
    private String profilePicUrl;
}
