package com.github.aayushjoshi2709.myvid.ioservice.service.impl.storage;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.ioservice.dto.storage.StorageResponse;
import com.github.aayushjoshi2709.myvid.ioservice.repository.S3StorageRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.StorageService;

@Service
@ConditionalOnProperty(prefix = "aws.s3.data-processing", name = "bucket-name")
public class S3VideoAndImageStorageService implements StorageService {
    @Value("${aws.s3.data-processing.bucket-name}")
    private String bucketName;
    private final S3StorageRepository s3StorageRepository;

    S3VideoAndImageStorageService(S3StorageRepository s3StorageRepository) {
        this.s3StorageRepository = s3StorageRepository;
    }

    @Override
    public StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData) {
        return this.s3StorageRepository.getPresignedUrl(bucketName, keyName, metaData);
    }

    @Override
    public String getDataFromStorage(String originalUrl) {
        return this.s3StorageRepository.getDataFromStorage(originalUrl);
    }

    @Override
    public String generateKey(String name) {
        String namePlusDate = name + "_" + LocalDateTime.now();
        return namePlusDate + UUID.randomUUID();
    }

}
