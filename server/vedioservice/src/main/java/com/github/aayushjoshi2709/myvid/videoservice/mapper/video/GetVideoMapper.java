package com.github.aayushjoshi2709.myvid.videoservice.mapper.video;

import org.mapstruct.Mapper;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetVideoMapper {
    @Mapping(source = "createdBy", target = "createdBy")
    GetVideoDto toDto(Video video);
    Video toEntity(GetVideoDto createVideoDto);
}