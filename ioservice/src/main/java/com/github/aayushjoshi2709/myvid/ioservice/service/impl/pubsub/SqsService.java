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

    public void sendMessage(String sendQueueUrl, Object messageBody) {
        sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(sendQueueUrl)
                        .messageBody(messageBody.toString())
                        .delaySeconds(10)
                        .build());
    }

    public List<Message> receiveMessage(String receiveQueueUrl) {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(receiveQueueUrl)
                    .maxNumberOfMessages(5)
                    .build();
            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            return messages;
        } catch (SqsException e) {
            log.error("An error occoured while trying to get messages from queue: {} \n error: {}", receiveQueueUrl,
                    e.fillInStackTrace());
        }
        return null;
    }
}
