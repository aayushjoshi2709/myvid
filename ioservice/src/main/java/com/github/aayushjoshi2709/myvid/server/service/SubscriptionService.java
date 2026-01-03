package com.github.aayushjoshi2709.myvid.server.service;

import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    void subscribeTo(UUID userId);
    void unSubscribeFrom(UUID userId);
    List<GetUserDto> getAllSubscriptions();
    List<GetVideoDto> getSubscriptionFeed();
}
