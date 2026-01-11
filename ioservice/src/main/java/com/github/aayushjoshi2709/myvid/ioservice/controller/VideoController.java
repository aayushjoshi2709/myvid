package com.github.aayushjoshi2709.myvid.ioservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.aayushjoshi2709.myvid.ioservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.service.VideoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<GetVideoDto>> getVideos() {
        return ResponseEntity.ok(this.videoService.getVideos());
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<GetVideoDto> getVideo(@PathVariable UUID videoId) {
        return ResponseEntity.ok(this.videoService.findById(videoId));
    }

    @PostMapping
    public ResponseEntity<GetVideoDto> addVideo(@RequestBody @Valid CreateVideoDto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.videoService.addVideo(entity));
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<GetVideoDto> updateVideo(@PathVariable UUID videoId,
            @RequestBody @Valid UpdateVideoDto updatedVideoData) {
        return ResponseEntity.ok(this.videoService.updateById(videoId, updatedVideoData));
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Video> deleteVideo(@PathVariable UUID videoId) {
        this.videoService.deleteVideoById(videoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
