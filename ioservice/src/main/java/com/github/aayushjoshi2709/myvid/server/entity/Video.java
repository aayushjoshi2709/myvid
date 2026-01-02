package com.github.aayushjoshi2709.myvid.server.entity;

import com.github.aayushjoshi2709.myvid.server.entity.Common.Common;

import com.github.aayushjoshi2709.myvid.server.entity.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Video extends Common {
    @Column(name = "thumbnailUrl", length = 50, nullable = false, unique = true)
    private String thumbnailUrl;

    @Column(name = "videoUrl", length = 50, nullable = false, unique = true)
    private String videoUrl;

    @Column(name = "title", length = 100, nullable = false, unique = true)
    private String title;

    @Column(name = "description", length = 500, nullable = false, unique = true)
    private String description;
    private Long viewCount;

    @Enumerated
    private VideoStatus status = VideoStatus.CREATED;

    @ManyToOne
    @JoinColumn(name="userId")
    private User createdBy;
}
