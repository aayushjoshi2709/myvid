package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.List;

import software.amazon.awssdk.services.sqs.model.Message;

public interface PubSubService {
    void sendMessage(String messageBody);

    List<Message> receiveMessages();
}