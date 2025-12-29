package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.Video;

import java.util.List;
import java.util.UUID;

public interface VideoService {
    List<Video> getVideos();
    Video findById(UUID id);
    Video addVideo(CreateVideoDto createVideo);
    Video updateById(UUID id, CreateVideoDto updatedVideoData);
    void deleteVideoById(UUID id);
}
