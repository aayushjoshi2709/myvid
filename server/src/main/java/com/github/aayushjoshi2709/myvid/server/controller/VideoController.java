package com.github.aayushjoshi2709.myvid.server.controller;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.aayushjoshi2709.myvid.server.entity.Video;
import com.github.aayushjoshi2709.myvid.server.service.VideoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vedio")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/")
    public ResponseEntity<List<Video>> getVideos() {
        return ResponseEntity.ok(this.videoService.getVideos());
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<Video> getVideo(@PathVariable UUID videoId){
        return ResponseEntity.ok(this.videoService.findById(videoId));
    }

    @PostMapping("/")
    public ResponseEntity<Video> addVideo(@RequestBody CreateVideoDto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.videoService.addVideo(entity));
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<Video> updateVideo(@PathVariable UUID videoId, @RequestBody CreateVideoDto updatedVideoData){
        return ResponseEntity.ok(this.videoService.updateById(videoId, updatedVideoData));
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Video> deleteVideo(@PathVariable UUID videoId){
        this.videoService.deleteVideoById(videoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
