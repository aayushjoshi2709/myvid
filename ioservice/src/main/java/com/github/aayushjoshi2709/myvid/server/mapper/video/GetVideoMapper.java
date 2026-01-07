package com.github.aayushjoshi2709.myvid.server.mapper.video;

import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDetailsForVideo;
import com.github.aayushjoshi2709.myvid.server.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.server.entity.Video;
import com.github.aayushjoshi2709.myvid.server.mapper.user.GetUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = GetUserDetailsForVideo.class)
public interface GetVideoMapper {
    GetVideoDto toDto(Video video);
    Video toEntity(GetVideoDto createVideoDto);
}