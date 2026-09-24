package com.github.aayushjoshi2709.myvid.videoservice.service;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.videoservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

public interface VideoService {
    List<GetVideoDto> getVideos(Integer Page, Integer Size);

    Video findVideoObjectById(UUID id);

    GetVideoDto findById(UUID id);

    GetVideoDto addVideo(HttpHeaders headers, CreateVideoDto createVideo);

    GetVideoDto updateById(HttpHeaders headers, UUID id, UpdateVideoDto updatedVideoData, boolean publishVideoEvent, boolean validateUserDetails);

    void deleteVideoById(HttpHeaders headers, UUID id);

    void getAndUpdateVideosFromQueue();
}
