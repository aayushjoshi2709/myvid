package com.github.aayushjoshi2709.myvid.server.service.impl.storage;

import com.github.aayushjoshi2709.myvid.server.dto.storage.StorageResponse;
import com.github.aayushjoshi2709.myvid.server.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@ConditionalOnProperty(name = "aws.s3.enabled", havingValue = "true")
@ConditionalOnProperty(name = "aws.s3.type", havingValue = "local")
@Service
public class S3LocalStorage implements StorageService {

        @Value("${aws.s3.bucketname}")
        private String bucketName;

        @Value("${aws.s3.region}")
        private String region;

        @Value("${aws.endpoint}")
        private String endpoint;

        @Value("${aws.access-key:test}")
        private String accessKey;

        @Value("${aws.secret-key:test}")
        private String secretKey;

        @Override
        public StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData) {

                try (S3Presigner presigner = S3Presigner.builder()
                                .endpointOverride(URI.create(endpoint))
                                .region(Region.of(region))
                                .credentialsProvider(
                                                StaticCredentialsProvider.create(
                                                                AwsBasicCredentials.create(accessKey, secretKey)))
                                .serviceConfiguration(S3Configuration.builder()
                                                .pathStyleAccessEnabled(true) 
                                                .build())
                                .build()) {

                        PutObjectRequest objectRequest = PutObjectRequest.builder()
                                        .bucket(bucketName)
                                        .key(keyName)
                                        .metadata(metaData)
                                        .build();

                        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                        .signatureDuration(Duration.ofMinutes(30))
                                        .putObjectRequest(objectRequest)
                                        .build();

                        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
                        String presignedUrl = presignedRequest.url().toString();
                        String originalUrl = endpoint + "/" + bucketName + "/" + keyName;
                        return new StorageResponse(presignedUrl, originalUrl);
                }
        }

        @Override
        public String getDataFromStorage(String originalUrl) {
                return "";
        }

        @Override
        public String generateKey(String name) {
                return name + "_" + LocalDateTime.now() + "_" + UUID.randomUUID();
        }
}
