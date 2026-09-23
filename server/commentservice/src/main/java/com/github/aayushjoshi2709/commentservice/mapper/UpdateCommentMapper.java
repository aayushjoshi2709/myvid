package com.github.aayushjoshi2709.commentservice.mapper;


import com.github.aayushjoshi2709.commentservice.dto.UpdateCommentDto;
import com.github.aayushjoshi2709.commentservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateCommentMapper {
    UpdateCommentDto toDto(Comment comment);
    Comment toEntity(UpdateCommentDto dto);
}
