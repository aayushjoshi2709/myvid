package com.github.aayushjoshi2709.myvid.encodingservice.service;

import com.github.aayushjoshi2709.myvid.encodingservice.dto.VideoDetailsDto;

public interface VideoEncodingService {
    public void encodeVideo(VideoDetailsDto videoDetails);
}