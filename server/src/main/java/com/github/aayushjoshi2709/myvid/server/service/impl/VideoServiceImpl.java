package com.github.aayushjoshi2709.myvid.server.service.impl;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.server.entity.Video;
import com.github.aayushjoshi2709.myvid.server.repository.VideoRepository;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public List<Video> getVideos() {
        return this.videoRepository.findAll();
    }

    @Override
    public Video findById(UUID id) {
        return null;
    }

    @Override
    public Video addVideo(CreateVideoDto createVideo) {
        Video video = new Video();
        return this.videoRepository.save(video);
    }

    @Override
    public Video updateById(UUID videoId, CreateVideoDto updatedVideoData) {
        return null;
    }

    @Override
    public void deleteVideoById(UUID id) {

    }
}
