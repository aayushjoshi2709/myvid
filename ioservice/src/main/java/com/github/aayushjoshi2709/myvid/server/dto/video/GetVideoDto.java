package com.github.aayushjoshi2709.myvid.server.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetVideoDto implements Serializable {
    private UUID id;
    private String thumbnailUrl;
    private String videoUrl;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
