package com.github.aayushjoshi2709.myvid.encodingservice.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Repository
public class S3StorageRepository {
        @Value("${aws.endpoint:#{null}}")
        private String endpointUrl;

        private final S3Presigner s3Presigner;

        S3StorageRepository(S3Presigner s3Presigner) {
                this.s3Presigner = s3Presigner;
        }

        public void getFileFromS3(
                String originalUrl, 
                String downloadPath
        ) {
        }

        public String saveFilesToS3(String originalUrl) {
                return "";
        }
}
