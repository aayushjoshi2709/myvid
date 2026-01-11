package com.github.aayushjoshi2709.myvid.ioservice.entity;

import com.github.aayushjoshi2709.myvid.ioservice.entity.Common.Common;
import com.github.aayushjoshi2709.myvid.ioservice.entity.enums.VideoStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Video extends Common {
    @Column(name = "thumbnailUrl", length = 150, nullable = false, unique = true)
    private String thumbnailUrl;

    @Column(name = "videoUrl", length = 150, nullable = false, unique = true)
    private String videoUrl;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 500, nullable = false)
    private String description;
    private Long viewCount;

    @Enumerated
    private VideoStatus status = VideoStatus.CREATED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;
}
