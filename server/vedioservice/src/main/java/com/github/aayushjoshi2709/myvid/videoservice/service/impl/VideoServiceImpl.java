package com.github.aayushjoshi2709.myvid.videoservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.videoservice.dto.user.UserDto;
import com.github.aayushjoshi2709.myvid.videoservice.repository.AuthServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import software.amazon.awssdk.services.sqs.model.Message;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;
import com.github.aayushjoshi2709.myvid.videoservice.entity.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.videoservice.mapper.video.CreateVideoMapper;
import com.github.aayushjoshi2709.myvid.videoservice.mapper.video.GetVideoMapper;
import com.github.aayushjoshi2709.myvid.videoservice.mapper.video.PublishVideoMapper;
import com.github.aayushjoshi2709.myvid.videoservice.mapper.video.UpdateVideoMapper;
import com.github.aayushjoshi2709.myvid.videoservice.repository.VideoRepository;
import com.github.aayushjoshi2709.myvid.videoservice.service.PubSubService;
import com.github.aayushjoshi2709.myvid.videoservice.service.VideoService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final AuthServiceRepository authServiceRepository;
    private final VideoRepository videoRepository;
    private final CreateVideoMapper createVideoMapper;
    private final GetVideoMapper getVideoMapper;
    private final UpdateVideoMapper updateVideoMapper;
    private final PubSubService pubSubService;
    private final ObjectMapper objectMapper;
    private final PublishVideoMapper publishVideoMapper;

    private String getUserIdFromHeader(HttpHeaders headers) {
        String userId = headers.getFirst("x-user-id");
        log.debug("Going to get user details for user id {}", userId);
        if(Objects.isNull(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return userId;
    }

    private UserDto getUserDetailsFromHeaders(HttpHeaders headers) {
        String userId = getUserIdFromHeader(headers);
        return this.authServiceRepository.getUserDetailsById(UUID.fromString(userId));
    }

    private void validateVideoUpdate(HttpHeaders headers, Video video) {
        if(!Objects.equals(video.getUserId().toString(), this.getUserIdFromHeader(headers))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (video.getStatus() == VideoStatus.DELETED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The video has already been deleted");
        }
    }

    public Video findVideoObjectById(UUID videoId) throws ResponseStatusException {
        log.info("Going to get video with id: {}", videoId);
        Video video = this.videoRepository.findById(videoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found for video id:" + videoId));
        log.info("Found video with id: {}", videoId);
        return video;
    }

    @Async
    public void publishVideoForProcessing(PublishVideoDto videoDto) {
        try {
            this.pubSubService.sendMessage(objectMapper.writeValueAsString(videoDto));
        } catch (JsonProcessingException e) {
            log.error("An error occurred while processing video: {}", Arrays.toString(e.getStackTrace()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while sending video for id:" + videoDto.getId());
        }
    }

    @Override
    public List<GetVideoDto> getVideos(Integer page, Integer size) {
        log.info("Going to get videos with page: {} and size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Video> videos = this.videoRepository.findAll(pageable);
        List<GetVideoDto> getVideosResponse = videos.stream().map(this.getVideoMapper::toDto).toList();
        log.info("videos found with page: {} and size: {} = {}", page, size, getVideosResponse);
        return getVideosResponse;
    }

    @Override
    public GetVideoDto findById(UUID videoId) throws ResponseStatusException {
        Video video = this.findVideoObjectById(videoId);
        GetVideoDto findByIdResponse = this.getVideoMapper.toDto(video);
        log.info("Got video for id: {} with values: {}", videoId, findByIdResponse);
        return findByIdResponse;
    }

    @Override
    @Transactional
    public GetVideoDto addVideo(HttpHeaders headers, CreateVideoDto createVideo) throws ResponseStatusException {
        UserDto user = this.getUserDetailsFromHeaders(headers);
        log.info("Going to add new video with following data: {}", createVideo);
        Video video = this.createVideoMapper.toEntity(createVideo);
        video.setUserId(user.id());
        Video savedVideo = this.videoRepository.save(video);
        GetVideoDto addVideoResponse = this.getVideoMapper.toDto(savedVideo);
        log.info("Video data saved successfully: {}", addVideoResponse);
        this.publishVideoForProcessing(this.publishVideoMapper.toDto(savedVideo));
        return addVideoResponse;
    }

    @Override
    public GetVideoDto updateById(HttpHeaders headers, UUID videoId, UpdateVideoDto updatedVideoData, boolean publishVideoEvent, boolean validateUserDetails)
            throws ResponseStatusException {
        log.info("Going to update video with id {} with the following data {}", videoId, updatedVideoData);
        Video video = this.findVideoObjectById(videoId);

        if(validateUserDetails) {
            this.validateVideoUpdate(headers, video);
        }

        String originalVideoUrl = video.getVideoUrl();
        log.info("Video data for id: {} before update: {}", videoId, video);
        this.updateVideoMapper.updateVideo(updatedVideoData, video);
        if (updatedVideoData.getVideoUrl() != null) {
            video.setStatus(VideoStatus.CREATED);
        }
        Video updatedVideo = this.videoRepository.save(video);
        GetVideoDto updateVideoResponse = getVideoMapper.toDto(updatedVideo);
        if (publishVideoEvent && !Objects.equals(originalVideoUrl, updatedVideo.getVideoUrl())) {
            this.publishVideoForProcessing(this.publishVideoMapper.toDto(updatedVideo));
        }
        log.info("Video data for id: {} after update {}", videoId, updateVideoResponse);
        return updateVideoResponse;
    }

    @Override
    public void deleteVideoById(HttpHeaders headers, UUID videoId) throws ResponseStatusException {
        log.info("Going to delete video with id: {}", videoId);
        Video video = this.findVideoObjectById(videoId);
        this.validateVideoUpdate(headers, video);
        video.setStatus(VideoStatus.DELETED);
        this.videoRepository.save(video);
        log.info("Video with id: {} deleted successfully", videoId);
    }

    @Override
    public void getAndUpdateVideosFromQueue() {
        List<Message> videoMessage = this.pubSubService.receiveMessages();
        log.info("Going to process video processed messages of size:  {}", videoMessage.size());
        for (Message message : videoMessage) {
            try {
                log.info("Going to process message with message id: {}", message.messageId());
                PublishVideoDto videoDetails = objectMapper.readValue(message.body(), PublishVideoDto.class);
                log.info("Here are the video details we got: {}", videoDetails);
                UpdateVideoDto updateVideoDto = UpdateVideoDto.builder()
                        .thumbnailUrl(videoDetails.getThumbnailUrl())
                        .videoUrl(videoDetails.getVideoUrl())
                        .build();
                this.updateById(null, videoDetails.getId(), updateVideoDto, false, false);
                log.info("Going to delete video processed event with data: {}", videoDetails);
                this.pubSubService.deleteMessage(message);
            } catch (JsonProcessingException | ResponseStatusException e) {
                log.error("Failed to decode json payload inside process encoding video for messageId: {}",
                        message.messageId(), e);
            }
        }
    }
}
