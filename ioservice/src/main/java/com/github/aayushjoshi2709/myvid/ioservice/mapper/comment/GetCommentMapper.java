package com.github.aayushjoshi2709.myvid.ioservice.mapper.comment;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.GetCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Comment;

@Mapper(componentModel = "spring")
public interface GetCommentMapper {
    GetCommentDto toDto(Comment comment);

    Comment toEntity(GetCommentDto commentDto);
}
