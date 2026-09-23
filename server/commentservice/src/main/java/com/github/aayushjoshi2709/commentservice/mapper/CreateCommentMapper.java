package com.github.aayushjoshi2709.commentservice.mapper;

import com.github.aayushjoshi2709.commentservice.dto.CreateCommentDto;
import com.github.aayushjoshi2709.commentservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateCommentMapper {
    CreateCommentDto toDto(Comment comment);

    Comment toEntity(CreateCommentDto dto);
}
