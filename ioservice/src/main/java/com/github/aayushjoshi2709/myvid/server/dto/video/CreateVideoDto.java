package com.github.aayushjoshi2709.myvid.server.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateVideoDto {
    private String thumbnailUrl;
    private String videoUrl;
    private String title;
    private String description;
    private Long viewCount;
}
