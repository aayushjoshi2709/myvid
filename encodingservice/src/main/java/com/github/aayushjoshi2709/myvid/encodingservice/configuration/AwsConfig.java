package com.github.aayushjoshi2709.myvid.encodingservice.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

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


    private AwsCredentialsProvider credentialsProvider() {
        if (accessKey != null && secretKey != null) {
            return StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey));
        }
        return DefaultCredentialsProvider.builder().build();
    }

    private <T extends AwsClientBuilder<T, ?>> T baseBuilder(T builder) {
        builder.region(Region.of(region))
               .credentialsProvider(credentialsProvider());

        if (endpoint != null) {
            builder.endpointOverride(URI.create(endpoint));
        }
        return builder;
    }

    private S3Configuration s3Config() {
        return pathAccess
                ? S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build()
                : null;
    }


    @Bean
    SqsClient sqsClient() {
        return baseBuilder(SqsClient.builder())
                .build();
    }

    @Bean
    S3Client s3Client() {
        S3ClientBuilder builder = baseBuilder(S3Client.builder());

        if (pathAccess) {
            builder.serviceConfiguration(s3Config());
        }
        return builder.build();
    }

    @Bean
    S3Presigner s3Presigner() {
        S3Presigner.Builder builder =
                S3Presigner.builder()
                        .region(Region.of(region))
                        .credentialsProvider(credentialsProvider());

        if (endpoint != null) {
            builder.endpointOverride(URI.create(endpoint));
        }
        if (pathAccess) {
            builder.serviceConfiguration(s3Config());
        }
        return builder.build();
    }

    @Bean
    S3TransferManager s3TransferManager() {
        S3AsyncClientBuilder builder =
                baseBuilder(S3AsyncClient.builder());

        if (pathAccess) {
            builder.serviceConfiguration(s3Config());
        }

        return S3TransferManager.builder()
                .s3Client(builder.build())
                .build();
    }
}
