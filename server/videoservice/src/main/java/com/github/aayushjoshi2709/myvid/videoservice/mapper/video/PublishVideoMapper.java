package com.github.aayushjoshi2709.myvid.videoservice.mapper.video;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.videoservice.dto.video.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;

@Mapper(componentModel = "spring")
public interface PublishVideoMapper {
    PublishVideoDto toDto(Video video);

    Video toEntity(PublishVideoDto publishVideoDto);
}
