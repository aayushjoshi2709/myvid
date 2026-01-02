package com.github.aayushjoshi2709.myvid.server.mapper.video;

import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateVideoMapper {
    CreateVideoDto toDto(Video video);
    Video toEntity(CreateVideoDto createVideoDto);
}
