package com.github.aayushjoshi2709.myvid.server.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import com.github.aayushjoshi2709.myvid.server.entity.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.server.mapper.video.CreateVideoMapper;
import com.github.aayushjoshi2709.myvid.server.mapper.video.GetVideoMapper;
import com.github.aayushjoshi2709.myvid.server.mapper.video.UpdateVideoMapper;
import com.github.aayushjoshi2709.myvid.server.repository.UserRepository;
import com.github.aayushjoshi2709.myvid.server.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.server.entity.Video;
import com.github.aayushjoshi2709.myvid.server.repository.VideoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CreateVideoMapper createVideoMapper;
    private final GetVideoMapper getVideoMapper;
    private final UpdateVideoMapper updateVideoMapper;
    private final UserRepository userRepository;

    private String getCurrentUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("User not authenticated");
        }
        return ((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername();
    }

    private User getCurrentUser() {
        String username = getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Video findByIdInDb(UUID id){
        return this.videoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<GetVideoDto> getVideos() {
        log.info("Going to get videos");
        List<Video> videos =  this.videoRepository.findAll();
        log.info("videos found = {}", videos);
        return videos.stream().map(this.getVideoMapper::toDto).toList();
    }

    @Override
    public GetVideoDto findById(UUID videoId) {
        return this.getVideoMapper.toDto(this.findByIdInDb(videoId));
    }

    @Override
    @Transactional
    public GetVideoDto addVideo(CreateVideoDto createVideo) {
        Video video = this.createVideoMapper.toEntity(createVideo);
        User user = getCurrentUser();
        video.setCreatedBy(user);
        Video savedVideo = this.videoRepository.save(video);
        return this.getVideoMapper.toDto(savedVideo);
    }

    @Override
    public GetVideoDto updateById(UUID videoId, UpdateVideoDto updatedVideoData) {
        Video video = this.findByIdInDb(videoId);
        this.updateVideoMapper.updateVideo(updatedVideoData, video);
        Video updatedVideo = this.videoRepository.save(video);
        return this.getVideoMapper.toDto(updatedVideo);
    }

    @Override
    public void deleteVideoById(UUID id) {
        Video video = this.findByIdInDb(id);
        video.setStatus(VideoStatus.DELETED);
        this.videoRepository.save(video);
    }
}
