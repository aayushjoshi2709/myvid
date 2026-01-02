package com.github.aayushjoshi2709.myvid.server.service.impl.storage;

import com.github.aayushjoshi2709.myvid.server.common.entity.StorageResponse;
import com.github.aayushjoshi2709.myvid.server.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.Map;

@ConditionalOnProperty(name="aws.s3.enabled", value = "true")
public class S3Storage implements StorageService {

    @Value("${aws.s3.bucketname}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Override
    public StorageResponse getPresignedUrl(String keyName, Map<String, String> metaData) {
        try(S3Presigner presigner = S3Presigner.create()){
            String originalUrl = "https://s3." + region + ".amazonaws.com/" + bucketName + "/" + keyName;
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
            return new StorageResponse(
                    presignedRequest.url().toExternalForm(),
                    originalUrl
            );
        }
    }

    @Override
    public String getDataFromStorage(String originalUrl) {
        return "";
    }
}
