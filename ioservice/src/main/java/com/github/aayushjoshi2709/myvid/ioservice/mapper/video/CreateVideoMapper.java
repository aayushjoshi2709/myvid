package com.github.aayushjoshi2709.myvid.ioservice.mapper.video;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;

@Mapper(componentModel = "spring")
public interface CreateVideoMapper {
    CreateVideoDto toDto(Video video);
    Video toEntity(CreateVideoDto createVideoDto);
}
