package com.github.aayushjoshi2709.myvid.server.mapper.video;

import com.github.aayushjoshi2709.myvid.server.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.CreateVideoDto;
import com.github.aayushjoshi2709.myvid.server.dto.video.UpdateVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import com.github.aayushjoshi2709.myvid.server.entity.Video;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UpdateVideoMapper {
    UpdateVideoDto toDto(Video video);
    Video toEntity(UpdateVideoDto updateVideoDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVideo(UpdateVideoDto updatedVideoDto,
                           @MappingTarget Video video);
}
