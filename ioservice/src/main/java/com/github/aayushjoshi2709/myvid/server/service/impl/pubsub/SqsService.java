package com.github.aayushjoshi2709.myvid.server.service.impl.pubsub;

import org.springframework.beans.factory.annotation.Value;

import com.github.aayushjoshi2709.myvid.server.service.PubSubService;

public class SqsService implements PubSubService {
    @Value("${aws.s3.video-processing.bucketname}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.endpoint}")
    private String endpoint;

    @Value("${aws.access-key:test}")
    private String accessKey;

    @Value("${aws.secret-key:test}")
    private String secretKey;
}
