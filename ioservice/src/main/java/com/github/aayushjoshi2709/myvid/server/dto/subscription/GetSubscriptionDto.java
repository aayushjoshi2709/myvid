package com.github.aayushjoshi2709.myvid.server.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriptionDto {
    private UUID id;
    private String username;
    private String profilePicUrl;
}
