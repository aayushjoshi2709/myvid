package com.github.aayushjoshi2709.myvid.server.dto.storage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StorageResponse {
    private String presignedUrl;
    private String originalUrl;
}
