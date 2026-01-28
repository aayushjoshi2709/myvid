package com.github.aayushjoshi2709.myvid.ioservice.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.GetUserDetailsForVideo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetVideoDto implements Serializable {
    private UUID id;
    private String thumbnailUrl;
    private String videoUrl;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private GetUserDetailsForVideo createdBy;
}
