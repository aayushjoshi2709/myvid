package com.github.aayushjoshi2709.myvid.ioservice.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriptionDto implements Serializable {
    private UUID id;
    private String username;
    private String profilePicUrl;
}
