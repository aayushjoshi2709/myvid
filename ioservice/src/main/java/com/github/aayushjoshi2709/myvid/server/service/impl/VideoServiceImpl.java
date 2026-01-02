package com.github.aayushjoshi2709.myvid.server.service.impl;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.server.mapper.video.CreateVideoMapper;
import com.github.aayushjoshi2709.myvid.server.mapper.video.GetVideoMapper;
import com.github.aayushjoshi2709.myvid.server.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.server.entity.Video;
import com.github.aayushjoshi2709.myvid.server.repository.VideoRepository;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CreateVideoMapper createVideoMapper;
    private final GetVideoMapper getVideoMapper;

    private Video findByIdInDb(UUID id){
        return this.videoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<GetVideoDto> getVideos() {
        List<Video> videos =  this.videoRepository.findAll();
        return videos.stream().map(this.getVideoMapper::toDto).toList();
    }

    @Override
    public GetVideoDto findById(UUID id) {
        return this.getVideoMapper.toDto(this.findByIdInDb(id));
    }

    @Override
    public GetVideoDto addVideo(CreateVideoDto createVideo) {
        Video video = this.createVideoMapper.toEntity(createVideo);
        Video savedVideo = this.videoRepository.save(video);
        return this.getVideoMapper.toDto(savedVideo);
    }

    @Override
    public GetVideoDto updateById(UUID videoId, CreateVideoDto updatedVideoData) {
        return null;
    }

    @Override
    public void deleteVideoById(UUID id) {
        Video video = this.findByIdInDb(id);
        video.setStatus(VideoStatus.DELETED);
        this.videoRepository.save(video);
    }
}
