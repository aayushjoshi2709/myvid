package com.github.aayushjoshi2709.myvid.encodingservice.service.impl.pubsub;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.encodingservice.repository.SqsRepository;
import com.github.aayushjoshi2709.myvid.encodingservice.service.PubSubService;

import software.amazon.awssdk.services.sqs.model.Message;

@Service
@ConditionalOnProperty(prefix = "aws.sqs.data-processing", name = "queue-url")
@ConditionalOnProperty(prefix = "aws.sqs.processed-data", name = "queue-url")
public class VedioProcessingSqsService implements PubSubService {

    @Value("${aws.sqs.processed-data.queue-url}")
    private String sendQueueUrl;
    @Value("${aws.sqs.data-processing.queue-url}")
    private String receiveQueueUrl;

    private SqsRepository sqsRepository;

    public VedioProcessingSqsService(SqsRepository sqsRepository) {
        this.sqsRepository = sqsRepository;
    }

    @Override
    public void sendMessage(String messageBody) {
        this.sqsRepository.sendMessage(sendQueueUrl, messageBody);
    }

    @Override
    public List<Message> receiveMessages() {
        return this.sqsRepository.receiveMessages(receiveQueueUrl);
    }
}
