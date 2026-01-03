package com.github.aayushjoshi2709.myvid.server.controller;

import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.server.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public ResponseEntity<List<GetUserDto>> getSubscriptions(){
        return ResponseEntity.ok(this.subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/feed")
    public ResponseEntity<List<GetVideoDto>> getSubscriptionsFeed(){
        return ResponseEntity.ok(this.subscriptionService.getSubscriptionFeed());
    }
}
