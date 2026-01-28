package com.github.aayushjoshi2709.myvid.ioservice.dto.video;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateVideoDto {
    @Size(max = 150, message = "Thumbnail URL can be max 50 characters")
    private String thumbnailUrl;

    @Size(max = 150, message = "Video URL can be max 50 characters")
    private String videoUrl;

    @Size(max = 100, message = "Title can be max 100 characters")
    private String title;

    @Size(max = 500, message = "Description can be max 500 characters")
    private String description;
}
