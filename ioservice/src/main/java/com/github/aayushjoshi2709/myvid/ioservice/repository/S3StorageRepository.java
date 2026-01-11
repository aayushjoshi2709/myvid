package com.github.aayushjoshi2709.myvid.ioservice.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.myvid.ioservice.dto.storage.StorageResponse;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.Map;

@Repository
@ConditionalOnProperty(name = "aws.s3")
public class S3StorageRepository {
    private final S3Presigner s3Presigner;

    S3StorageRepository(S3Presigner s3Presigner) {
        this.s3Presigner = s3Presigner;
    }

    public StorageResponse getPresignedUrl(
            String bucketName,
            String keyName,
            Map<String, String> metaData) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .metadata(metaData)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(putObjectRequest)
                .signatureDuration(Duration.ofMinutes(30))
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        return new StorageResponse(
                presignedRequest.url().toString(),
                presignedRequest.httpRequest().getUri().toString());
    }

    public String getDataFromStorage(String originalUrl) {
        return "";
    }
}
