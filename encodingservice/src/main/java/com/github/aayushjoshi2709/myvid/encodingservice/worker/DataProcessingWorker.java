package com.github.aayushjoshi2709.myvid.encodingservice.worker;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataProcessingWorker {
    private final SqsClient sqsClient;

    
}
