package com.github.aayushjoshi2709.myvid.ioservice.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.myvid.ioservice.dto.storage.StorageResponse;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.Map;

@Repository
public class S3StorageRepository {
    @Value("${aws.endpoint:#{null}}")
    private String endpointUrl;

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

        String originalUrl = String.format(
                "%s/%s/%s",
                this.endpointUrl,
                bucketName,
                keyName);

        return new StorageResponse(
                presignedRequest.url().toString(),
                originalUrl);
    }

    public String getDataFromStorage(String originalUrl) {
        return "";
    }
}
