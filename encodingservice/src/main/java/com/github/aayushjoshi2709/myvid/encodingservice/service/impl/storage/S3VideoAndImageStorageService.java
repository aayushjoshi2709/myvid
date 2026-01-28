package com.github.aayushjoshi2709.myvid.encodingservice.service.impl.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.encodingservice.repository.S3StorageRepository;
import com.github.aayushjoshi2709.myvid.encodingservice.service.StorageService;

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
    public void getFileFromStorage(String fileUrl, String downloadPath) {
        this.s3StorageRepository.getFileFromS3(fileUrl, downloadPath);
    }

    @Override
    public void writeFileToStorage(String fileUrl) {
        this.s3StorageRepository.saveFilesToS3(fileUrl);
        throw new UnsupportedOperationException("Unimplemented method 'addFilesToStorage'");
    }

}
