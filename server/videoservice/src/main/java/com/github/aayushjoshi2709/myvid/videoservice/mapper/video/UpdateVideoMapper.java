package com.github.aayushjoshi2709.myvid.videoservice.mapper.video;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.github.aayushjoshi2709.myvid.videoservice.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;

@Mapper(componentModel = "spring")
public interface UpdateVideoMapper {
    UpdateVideoDto toDto(Video video);
    Video toEntity(UpdateVideoDto updateVideoDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVideo(UpdateVideoDto updatedVideoDto,
                           @MappingTarget Video video);
}
