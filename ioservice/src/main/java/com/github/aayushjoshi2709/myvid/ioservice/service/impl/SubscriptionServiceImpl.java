package com.github.aayushjoshi2709.myvid.ioservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.ioservice.dto.subscription.GetSubscriptionDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.service.SubscriptionService;
import com.github.aayushjoshi2709.myvid.ioservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserService userService;

    @Override
    public void subscribeTo(UUID userId) {
        
    }

    @Override
    public void unSubscribeFrom(UUID userId) {

    }

    @Override
    public List<GetSubscriptionDto> getAllSubscriptions() {
        return List.of();
    }

    @Override
    public List<GetVideoDto> getSubscriptionFeed() {
        return List.of();
    }
}
