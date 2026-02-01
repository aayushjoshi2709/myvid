package com.github.aayushjoshi2709.myvid.ioservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.aayushjoshi2709.myvid.ioservice.dto.subscription.GetSubscriptionDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.service.SubscriptionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PutMapping("/{userId}")
    public ResponseEntity<Void> subscribeUser(@PathVariable UUID userId){
        this.subscriptionService.subscribeTo(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> unsubscribeUser(@PathVariable UUID userId){
        this.subscriptionService.unSubscribeFrom(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GetSubscriptionDto>> getSubscriptions(){
        return ResponseEntity.ok(this.subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/feed")
    public ResponseEntity<List<GetVideoDto>> getSubscriptionsFeed(){
        return ResponseEntity.ok(this.subscriptionService.getSubscriptionFeed());
    }
}
