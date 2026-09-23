package com.github.aayushjoshi2709.commentservice.mapper;

import com.github.aayushjoshi2709.commentservice.dto.GetCommentDto;
import com.github.aayushjoshi2709.commentservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetCommentMapper {
    GetCommentDto toDto(Comment comment);
    Comment toEntity(GetCommentDto dto);
}
