package com.github.aayushjoshi2709.myvid.ioservice.dto.storage;

import com.github.aayushjoshi2709.myvid.ioservice.dto.storage.enums.StorageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StorageRequest {
    private StorageType storageType;
    private String name;
}
