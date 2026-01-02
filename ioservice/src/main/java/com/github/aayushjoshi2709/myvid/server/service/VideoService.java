package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.Video;

import java.util.List;
import java.util.UUID;

public interface VideoService {
    List<GetVideoDto> getVideos();
    GetVideoDto findById(UUID id);
    GetVideoDto addVideo(CreateVideoDto createVideo);
    GetVideoDto updateById(UUID id, UpdateVideoDto updatedVideoData);
    void deleteVideoById(UUID id);
}
