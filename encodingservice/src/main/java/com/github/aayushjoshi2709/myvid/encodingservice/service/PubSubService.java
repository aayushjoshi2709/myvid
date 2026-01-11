package com.github.aayushjoshi2709.myvid.encodingservice.service;

import java.util.List;

import software.amazon.awssdk.services.sqs.model.Message;

public interface PubSubService {
    void sendMessage(String sendQueueUrl, Object messageBody);

    List<Message> receiveMessage(String receiveQueueUrl);
}