package com.github.aayushjoshi2709.myvid.ioservice.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

@RequiredArgsConstructor
@Log4j2
@Repository
@ConditionalOnProperty(name = "aws.sqs")
public class SqsRepository {
    private final SqsClient sqsClient;

    @Async
    public void sendMessage(String sendQueueUrl, String messageBody) {
        sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(sendQueueUrl)
                        .messageBody(messageBody)
                        .delaySeconds(10)
                        .build());
    }

    public List<Message> receiveMessages(String receiveQueueUrl) {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(receiveQueueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(20)
                .visibilityTimeout(30)
                .build();

        try {
            List<Message> messages = sqsClient.receiveMessage(request).messages();
            return messages;

        } catch (SqsException e) {
            log.error(
                    "Failed to receive messages from queue {}. AWS error code={}, message={}",
                    receiveQueueUrl,
                    e.awsErrorDetails().errorCode(),
                    e.awsErrorDetails().errorMessage(),
                    e);
            return Collections.emptyList();
        }
    }

}
