package com.github.aayushjoshi2709.myvid.ioservice.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.GetCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Comment;

@Mapper(componentModel = "spring")
public interface GetCommentMapper {
    @Mapping(source = "video.id", target = "vedioId")
    GetCommentDto toDto(Comment comment);

    @Mapping(target = "video", ignore = true)
    Comment toEntity(GetCommentDto commentDto);
}
