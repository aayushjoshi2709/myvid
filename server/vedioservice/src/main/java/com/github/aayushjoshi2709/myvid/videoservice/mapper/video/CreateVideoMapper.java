package com.github.aayushjoshi2709.myvid.videoservice.mapper.video;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.videoservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;

@Mapper(componentModel = "spring")
public interface CreateVideoMapper {
    CreateVideoDto toDto(Video video);
    Video toEntity(CreateVideoDto createVideoDto);
}
