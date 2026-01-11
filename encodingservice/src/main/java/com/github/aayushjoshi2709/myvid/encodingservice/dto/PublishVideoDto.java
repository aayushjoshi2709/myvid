package com.github.aayushjoshi2709.myvid.encodingservice.dto;

import java.util.UUID;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.enums.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublishVideoDto {
    private UUID id;
    private String thumbnailUrl;
    private String videoUrl;
    private VideoStatus status;
}
