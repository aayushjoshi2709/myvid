package com.github.aayushjoshi2709.myvid.ioservice.service.impl;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.myvid.ioservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.entity.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.CreateVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.GetVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.UpdateVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.repository.VideoRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.PubSubServices;
import com.github.aayushjoshi2709.myvid.ioservice.service.UserService;
import com.github.aayushjoshi2709.myvid.ioservice.service.VideoService;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CreateVideoMapper createVideoMapper;
    private final GetVideoMapper getVideoMapper;
    private final UpdateVideoMapper updateVideoMapper;
    private final UserService userService;
    private final PubSubService PubSubService;

    private Video findVideoById(UUID videoId) {
        return this.videoRepository.findById(videoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found for video id:" + videoId));
    }

    @Override
    public List<GetVideoDto> getVideos() {
        log.info("Going to get videos");
        List<Video> videos = this.videoRepository.findAll();
        List<GetVideoDto> getVideosResponse = videos.stream().map(this.getVideoMapper::toDto).toList();
        log.info("videos found = {}", getVideosResponse);
        return getVideosResponse;
    }

    @Override
    public GetVideoDto findById(UUID videoId) {
        log.info("Going to get video with id: {}", videoId);
        Video video = this.findVideoById(videoId);
        GetVideoDto findByIdResponse = this.getVideoMapper.toDto(video);
        log.info("Got video for id: {} with values: {}", videoId, findByIdResponse);
        return findByIdResponse;
    }

    @Override
    @Transactional
    public GetVideoDto addVideo(CreateVideoDto createVideo) {
        log.info("Going to add new video with following data: {}", createVideo);
        Video video = this.createVideoMapper.toEntity(createVideo);
        User user = this.userService.getCurrentUserDetails();
        video.setCreatedBy(user);
        Video savedVideo = this.videoRepository.save(video);
        GetVideoDto addVideoResponse = this.getVideoMapper.toDto(savedVideo);
        log.info("Video data saved successfully: {}", addVideoResponse);
        return addVideoResponse;
    }

    @Override
    public GetVideoDto updateById(UUID videoId, UpdateVideoDto updatedVideoData) {
        log.info("Going to update video with id {} with the following data {}", videoId, updatedVideoData);
        Video video = this.findVideoById(videoId);
        log.info("Video data for id: {} before update: {}", videoId, video);
        this.updateVideoMapper.updateVideo(updatedVideoData, video);
        Video updatedVideo = this.videoRepository.save(video);
        GetVideoDto updateVideoResponse = getVideoMapper.toDto(updatedVideo);
        log.info("Video data for id: {} after update {}", videoId, updateVideoResponse);
        return updateVideoResponse;
    }

    @Override
    public void deleteVideoById(UUID videoId) {
        log.info("Going to delete video with id: {}", videoId);
        Video video = this.findVideoById(videoId);
        video.setStatus(VideoStatus.DELETED);
        this.videoRepository.save(video);
        log.info("Video with id: {} deleted successfully", videoId);
    }
}
