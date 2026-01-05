package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.storage.StorageResponse;

import java.util.Map;

public interface StorageService {
    StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData);
    String getDataFromStorage(String originalUrl);
}
