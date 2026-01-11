package com.github.aayushjoshi2709.myvid.encodingservice.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

@Configuration
public class AwsConfig {
    @Value("${aws.region}")
    private String region;

    @Value("${aws.endpoint:#{null}}")
    private String endpoint;

    @Value("${aws.access-key:#{null}}")
    private String accessKey;

    @Value("${aws.secret-key:#{null}}")
    private String secretKey;

    @Value("${aws.pathAccess:true}")
    private boolean pathAccess;

    @Bean
    SqsClient sqsClient() {
        SqsClientBuilder builder = SqsClient.builder();
        builder.region(Region.of(region));

        if (endpoint != null) {
            builder.endpointOverride(URI.create(endpoint));
        }

        if (accessKey != null && secretKey != null) {
            builder.credentialsProvider(
                    StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)));
        } else {
            builder.credentialsProvider(
                    DefaultCredentialsProvider.builder().build());
        }

        return builder.build();
    }

    @Bean
    S3Client s3Client() {
        S3ClientBuilder builder = S3Client.builder();
        builder.region(Region.of(region));

        if (pathAccess == true) {
            builder.serviceConfiguration(
                    S3Configuration.builder()
                            .pathStyleAccessEnabled(true)
                            .build());
        }

        if (endpoint != null) {
            builder.endpointOverride(URI.create(endpoint));
        }

        if (accessKey != null && secretKey != null) {
            builder.credentialsProvider(
                    StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)));
        } else {
            builder.credentialsProvider(
                    DefaultCredentialsProvider.builder().build());
        }

        return builder.build();
    }

    @Bean
    S3Presigner s3Presigner() {
        S3Presigner.Builder builder = S3Presigner.builder();

        builder.region(Region.of(region));

        if (pathAccess == true) {
            builder.serviceConfiguration(
                    S3Configuration.builder()
                            .pathStyleAccessEnabled(true)
                            .build());
        }

        if (endpoint != null) {
            builder.endpointOverride(URI.create(endpoint));
        }

        if (accessKey != null && secretKey != null) {
            builder.credentialsProvider(
                    StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)));
        } else {
            builder.credentialsProvider(
                    DefaultCredentialsProvider.builder().build());
        }

        return builder.build();
    }
}
