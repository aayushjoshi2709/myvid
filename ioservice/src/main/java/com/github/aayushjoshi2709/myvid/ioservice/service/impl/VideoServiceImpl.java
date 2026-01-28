package com.github.aayushjoshi2709.myvid.ioservice.service.impl;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.model.Message;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.entity.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.CreateVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.GetVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.PublishVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.video.UpdateVideoMapper;
import com.github.aayushjoshi2709.myvid.ioservice.repository.VideoRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.PubSubService;
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
    private final PubSubService pubSubService;
    private final ObjectMapper objectMapper;
    private final PublishVideoMapper publishVideoMapper;

    private Video findVideoById(UUID videoId) {
        log.info("Going to get video with id: {}", videoId);
        Video video = this.videoRepository.findById(videoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found for video id:" + videoId));
        log.info("Found video with id: {}", videoId);
        return video;
    }

    @Async
    private void publishVedioForProcessing(PublishVideoDto vedioData) {
        try {
            this.pubSubService.sendMessage(objectMapper.writeValueAsString(vedioData));
        } catch (JsonProcessingException e) {
            log.error("An error occoured while processing vedio: {}", e.getStackTrace().toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occoured while sending vedio for id:" + vedioData.getId());
        }
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
        this.publishVedioForProcessing(this.publishVideoMapper.toDto(savedVideo));
        return addVideoResponse;
    }

    @Override
    public GetVideoDto updateById(UUID videoId, UpdateVideoDto updatedVideoData) {
        log.info("Going to update video with id {} with the following data {}", videoId, updatedVideoData);
        Video video = this.findVideoById(videoId);

        if (video.getStatus() == VideoStatus.DELETED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The vedio has already been deleted");
        }

        String originalVedioUrl = video.getVideoUrl();

        log.info("Video data for id: {} before update: {}", videoId, video);
        this.updateVideoMapper.updateVideo(updatedVideoData, video);
        if (updatedVideoData.getVideoUrl() != null) {
            video.setStatus(VideoStatus.CREATED);
        }
        Video updatedVideo = this.videoRepository.save(video);
        GetVideoDto updateVideoResponse = getVideoMapper.toDto(updatedVideo);
        if (originalVedioUrl != updatedVideo.getVideoUrl()) {
            this.publishVedioForProcessing(this.publishVideoMapper.toDto(updatedVideo));
        }
        log.info("Video data for id: {} after update {}", videoId, updateVideoResponse);
        return updateVideoResponse;
    }

    @Override
    public void deleteVideoById(UUID videoId) {
        log.info("Going to delete video with id: {}", videoId);
        Video video = this.findVideoById(videoId);
        if (video.getStatus() == VideoStatus.DELETED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The vedio has already been deleted");
        }
        video.setStatus(VideoStatus.DELETED);
        this.videoRepository.save(video);
        log.info("Video with id: {} deleted successfully", videoId);
    }

    @Override
    public void getAndUpdateVideosFromQueue() {
        List<Message> videoMessage = this.pubSubService.receiveMessages();
        log.info("Going to process vedio processed messages of size:  {}", videoMessage.size());
        for (Message message : videoMessage) {
            try {
                PublishVideoDto videoDetails = objectMapper.readValue(message.body(), PublishVideoDto.class);
                log.info("Here are the video details we got: {}", videoDetails);
                UpdateVideoDto updateVideoDto = UpdateVideoDto.builder()
                        .thumbnailUrl(videoDetails.getThumbnailUrl())
                        .videoUrl(videoDetails.getVideoUrl())
                        .build();
                this.updateById(videoDetails.getId(), updateVideoDto);
                log.info("Going to delete vedio processed event with data: ", videoDetails);
                this.pubSubService.deleteMessage(message);
            } catch (JsonProcessingException e) {
                log.error("Failed to decode json payload inside process encoding vedio for messageId: {} {}",
                        message.messageId(), e);
            }
        }
    }
}
