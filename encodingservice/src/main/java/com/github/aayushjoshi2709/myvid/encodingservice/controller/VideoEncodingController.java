package com.github.aayushjoshi2709.myvid.encodingservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aayushjoshi2709.myvid.encodingservice.dto.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.encodingservice.service.VideoEncodingService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/video/encode")
@RequiredArgsConstructor
public class VideoEncodingController {
    private VideoEncodingService videoEncodingService;

    @PostMapping("/")
    public ResponseEntity<Void> encodeVedio(@RequestBody PublishVideoDto entity) {
        this.videoEncodingService.encodeVideo(entity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
