package com.github.aayushjoshi2709.myvid.ioservice.service.impl.pubsub;

import java.util.List;

import com.github.aayushjoshi2709.myvid.ioservice.service.PubSubService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

@RequiredArgsConstructor
@Log4j2
public class SqsService implements PubSubService {
    private final SqsClient sqsClient;

    @Async
    public void sendMessage(String sendQueueUrl, String messageBody) {
        log.info("Going to send message to queue: {} \n message:{}", sendQueueUrl, messageBody.toString());
        sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(sendQueueUrl)
                        .messageBody(messageBody)
                        .delaySeconds(10)
                        .build());
        log.info("Message send successfully");
    }

    public List<Message> receiveMessage(String receiveQueueUrl) {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(receiveQueueUrl)
                    .maxNumberOfMessages(5)
                    .build();
            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            log.info("Received: {} messages from {}", messages.size(), receiveQueueUrl);
            return messages;
        } catch (SqsException e) {
            log.error("An error occoured while trying to get messages from queue: {} \n error: {}", receiveQueueUrl,
                    e.fillInStackTrace());
        }
        return null;
    }
}
