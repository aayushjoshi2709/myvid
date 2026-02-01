package com.github.aayushjoshi2709.myvid.encodingservice.service.impl.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.encodingservice.repository.S3StorageRepository;
import com.github.aayushjoshi2709.myvid.encodingservice.service.StorageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(prefix = "aws.s3.data-processing", name = "bucket-name")
public class S3VideoAndImageStorageService implements StorageService {
    @Value("${aws.s3.data-processing.bucket-name}")
    private String bucketName;
    private final S3StorageRepository s3StorageRepository;

    S3VideoAndImageStorageService(S3StorageRepository s3StorageRepository) {
        this.s3StorageRepository = s3StorageRepository;
    }

    public void getFileByKey(String key, String downloadPath) {
        this.s3StorageRepository.getFileFromS3(key, downloadPath);
        log.info("Got file from s3 successfully and saved it to {}", downloadPath);
    }

    @Override
    public void getFileByUrl(String fileUrl, String downloadPath) {
        log.info("Going to get file from s3 with path: {}", fileUrl);
        String[] urlParts = fileUrl.split("/");
        String key = urlParts[urlParts.length - 1];
        this.getFileByKey(key, downloadPath);
    }

    @Override
    public String uploadFolder(String prefix, String folderPath) {
        return this.s3StorageRepository.saveFolderToS3(prefix, folderPath);
    }

    @Override
    public void uploadFile(String filePath) {
        this.s3StorageRepository.saveFileToS3(filePath);
    }

}
