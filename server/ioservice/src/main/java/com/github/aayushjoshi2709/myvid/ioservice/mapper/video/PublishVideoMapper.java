package com.github.aayushjoshi2709.myvid.ioservice.mapper.video;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.video.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;

@Mapper(componentModel = "spring")
public interface PublishVideoMapper {
    PublishVideoDto toDto(Video video);

    Video toEntity(PublishVideoDto publishVideoDto);
}
