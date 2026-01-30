package com.github.aayushjoshi2709.myvid.ioservice.worker;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.aayushjoshi2709.myvid.ioservice.service.VideoService;

@Component
@RequiredArgsConstructor
@Slf4j
public class VedioEventProcessiongWorker {
    private final VideoService videoService;

    @Scheduled(fixedRate = 5000)
    public void pollQueue() {
        this.videoService.getAndUpdateVideosFromQueue();
    }
}
