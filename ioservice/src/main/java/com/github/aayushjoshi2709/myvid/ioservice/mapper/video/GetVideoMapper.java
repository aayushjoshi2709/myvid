package com.github.aayushjoshi2709.myvid.ioservice.mapper.video;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.GetUserDetailsForVideo;
import com.github.aayushjoshi2709.myvid.ioservice.dto.video.GetVideoDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.user.GetUserMapper;

@Mapper(componentModel = "spring", uses = GetUserDetailsForVideo.class)
public interface GetVideoMapper {
    GetVideoDto toDto(Video video);
    Video toEntity(GetVideoDto createVideoDto);
}