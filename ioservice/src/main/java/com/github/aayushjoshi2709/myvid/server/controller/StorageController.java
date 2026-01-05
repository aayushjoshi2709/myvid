package com.github.aayushjoshi2709.myvid.server.controller;

import com.github.aayushjoshi2709.myvid.server.dto.storage.StorageRequest;
import com.github.aayushjoshi2709.myvid.server.dto.storage.StorageResponse;
import com.github.aayushjoshi2709.myvid.server.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;
    @PostMapping("/presignedUrl")
    public ResponseEntity<StorageResponse> getPresignedUrl(@RequestBody StorageRequest storageRequested){
        String key = this.storageService.generateKey(storageRequested.getName());
        HashMap<String, String> metaData = new HashMap<>();
        String storageType = storageRequested.getStorageType().toString();
        metaData.put("storageType", storageType);
        return ResponseEntity.ok(this.storageService.getPresignedUrl(key,metaData));
    }
}
