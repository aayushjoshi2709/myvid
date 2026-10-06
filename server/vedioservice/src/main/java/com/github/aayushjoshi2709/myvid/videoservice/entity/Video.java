package com.github.aayushjoshi2709.myvid.videoservice.entity;

import java.util.UUID;

import com.github.aayushjoshi2709.myvid.videoservice.dto.user.UserDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Common.Common;
import com.github.aayushjoshi2709.myvid.videoservice.entity.enums.VideoStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Video extends Common {
    @Column(name = "thumbnailUrl", length = 150, nullable = false)
    private String thumbnailUrl;

    @Column(name = "videoUrl", length = 150, nullable = false)
    private String videoUrl;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 500, nullable = false)
    private String description;
    private Long viewCount;

    @Enumerated
    private VideoStatus status = VideoStatus.CREATED;

    @Column(name = "user_id")
    private UUID userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private UserDto createdBy;
}
