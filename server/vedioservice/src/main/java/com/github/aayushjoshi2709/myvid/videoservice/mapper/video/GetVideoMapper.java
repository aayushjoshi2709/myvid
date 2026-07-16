package com.github.aayushjoshi2709.myvid.videoservice.mapper.video;

import org.mapstruct.Mapper;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;

@Mapper(componentModel = "spring")
public interface GetVideoMapper {
    GetVideoDto toDto(Video video);
    Video toEntity(GetVideoDto createVideoDto);
}