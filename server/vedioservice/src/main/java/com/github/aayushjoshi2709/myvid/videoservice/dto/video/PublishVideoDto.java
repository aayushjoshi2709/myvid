package com.github.aayushjoshi2709.myvid.videoservice.dto.video;

import java.util.UUID;
import com.github.aayushjoshi2709.myvid.videoservice.entity.enums.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PublishVideoDto {
    private UUID id;
    private String thumbnailUrl;
    private String videoUrl;
    private VideoStatus status;
}
