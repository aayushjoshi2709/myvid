package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.Map;

import com.github.aayushjoshi2709.myvid.ioservice.dto.storage.StorageResponse;

public interface StorageService {
    StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData);
    String getDataFromStorage(String originalUrl);
    String generateKey(String name);
}
