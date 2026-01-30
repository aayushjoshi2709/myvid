package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;

public interface VideoService {
    List<GetVideoDto> getVideos(Integer Page, Integer Size);

    Video findVideoObjectById(UUID id);

    GetVideoDto findById(UUID id);

    GetVideoDto addVideo(CreateVideoDto createVideo);

    GetVideoDto updateById(UUID id, UpdateVideoDto updatedVideoData, boolean publishVideoEvent);

    void deleteVideoById(UUID id);

    void getAndUpdateVideosFromQueue();
}
