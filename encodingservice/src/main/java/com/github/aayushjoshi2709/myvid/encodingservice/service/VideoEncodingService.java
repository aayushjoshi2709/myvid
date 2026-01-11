package com.github.aayushjoshi2709.myvid.encodingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.PublishVideoDto;

public interface VideoEncodingService {
    public void encodeVideo(PublishVideoDto videoDetails);

    public void processEncodingVideo();
}