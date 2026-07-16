package com.github.aayushjoshi2709.myvid.videoservice.service;

import java.util.Map;

import com.github.aayushjoshi2709.myvid.videoservice.dto.storage.StorageResponse;

public interface StorageService {
    StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData);

    String getDataFromStorage(String originalUrl);

    String generateKey(String name);
}
