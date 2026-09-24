package com.github.aayushjoshi2709.commentservice.service.impl;

import com.github.aayushjoshi2709.commentservice.dto.CreateCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.GetCommentDto;
import com.github.aayushjoshi2709.commentservice.dto.UpdateCommentDto;
import com.github.aayushjoshi2709.commentservice.entity.Comment;
import com.github.aayushjoshi2709.commentservice.mapper.CreateCommentMapper;
import com.github.aayushjoshi2709.commentservice.mapper.GetCommentMapper;
import com.github.aayushjoshi2709.commentservice.repository.CommentRepository;
import com.github.aayushjoshi2709.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private CreateCommentMapper createCommentMapper;
    private GetCommentMapper getCommentMapper;

    public GetCommentDto findById(@PathVariable UUID id){
        Comment comment = this.commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found")
        );
        return getCommentMapper.toDto(comment);
    }

    public List<GetCommentDto> findAll(){
        return this.commentRepository.findAll().stream().map(getCommentMapper::toDto).toList();
    }

    public GetCommentDto create(CreateCommentDto createCommentDto) {
        Comment comment = this.createCommentMapper.toEntity(createCommentDto);
        Comment savedComment = this.commentRepository.save(comment);
        return getCommentMapper.toDto(savedComment);
    }

    public GetCommentDto update(UUID id, UpdateCommentDto updateCommentDto) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found")
        );

        if(updateCommentDto.comment() != null){
            comment.setComment(updateCommentDto.comment());
        }

        Comment savedComment = this.commentRepository.save(comment);
        return getCommentMapper.toDto(savedComment);
    }

    public void delete(UUID id) {
        this.commentRepository.deleteById(id);
    }
}
