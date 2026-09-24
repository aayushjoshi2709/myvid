package com.github.aayushjoshi2709.commentservice.service;

import com.github.aayushjoshi2709.commentservice.dto.CreateCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.GetCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.UpdateCommentDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    public GetCommentDto findById(@PathVariable UUID id);

    public List<GetCommentDto> findAll();

    public GetCommentDto create(CreateCommentDto createCommentDto);

    public GetCommentDto update(UUID id, UpdateCommentDto updateCommentDto);

    public void delete(UUID id);
}
