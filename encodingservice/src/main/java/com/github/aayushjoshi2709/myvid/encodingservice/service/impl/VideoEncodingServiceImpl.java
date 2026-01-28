package com.github.aayushjoshi2709.myvid.encodingservice.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.encodingservice.service.PubSubService;
import com.github.aayushjoshi2709.myvid.encodingservice.service.StorageService;
import com.github.aayushjoshi2709.myvid.encodingservice.service.VideoEncodingService;
import java.util.List;

import software.amazon.awssdk.services.sqs.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoEncodingServiceImpl implements VideoEncodingService {
    private final StorageService storageService;
    private final PubSubService pubSubService;
    private final ObjectMapper objectMapper;

    @Override
    public void encodeVideo(PublishVideoDto videoDetails) {
        try {
            this.storageService.getFileFromStorage(videoDetails.getVideoUrl(), "/temp/" + videoDetails.getId());
            this.storageService.writeFileToStorage("/temp" + videoDetails.getId());
            videoDetails.setStatus(VideoStatus.SAVED);
            this.pubSubService.sendMessage(objectMapper.writeValueAsString(videoDetails));
        } catch (JsonProcessingException e) {
            log.error("Failed to send vedio processed event for id:{} \n error:{}", videoDetails.getId(),
                    e.getStackTrace().toString());
        }
    }

    public void processEncodingVideo() {
        List<Message> videoMessage = this.pubSubService.receiveMessages();
        while (videoMessage.size() > 0) {
            log.info("Going to process {} messages", videoMessage.size());
            for (Message message : videoMessage) {
                try {
                    PublishVideoDto videoDetails = objectMapper.readValue(message.body(), PublishVideoDto.class);
                    this.encodeVideo(videoDetails);
                } catch (JsonProcessingException e) {
                    log.error("Failed to decode json payload inside process encoding vedio for messageId: {} {}",
                            message.messageId(), e.getStackTrace().toString());
                }

            }
            videoMessage = this.pubSubService.receiveMessages();
        }
    }
}
