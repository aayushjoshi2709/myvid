package com.github.aayushjoshi2709.myvid.encodingservice.worker;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.aayushjoshi2709.myvid.encodingservice.service.VideoEncodingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class VideoInfoProcessingWorker {
    private final VideoEncodingService videoEncodingService;

    @Scheduled(fixedDelay = 5000)
    public void pollQueue() {
        this.videoEncodingService.processEncodingVideo();
    }
}
