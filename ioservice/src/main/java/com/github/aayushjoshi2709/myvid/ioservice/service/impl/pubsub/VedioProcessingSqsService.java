package com.github.aayushjoshi2709.myvid.ioservice.service.impl.pubsub;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.ioservice.repository.SqsRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.PubSubService;

import software.amazon.awssdk.services.sqs.model.Message;

@Service
@ConditionalOnProperty("aws.sqs")
public class VedioProcessingSqsService implements PubSubService {

    @Value("${aws.sqs.data-processing.queue-url}")
    private String sendQueueUrl;
    @Value("${aws.sqs.processed-data.queue-url}")
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
