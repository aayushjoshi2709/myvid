package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.subscription.GetSubscriptionDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;

public interface SubscriptionService {
    void subscribeTo(UUID userId);
    void unSubscribeFrom(UUID userId);
    List<GetSubscriptionDto> getAllSubscriptions();
    List<GetVideoDto> getSubscriptionFeed();
}
