package com.github.aayushjoshi2709.myvid.ioservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.AddCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.GetCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/video/{videoId}/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<GetCommentDto> getComments(
            @PathVariable UUID videoId,
            @RequestParam(required = false) UUID parentCommentId,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer page) {
        return this.commentService.getComments(videoId, parentCommentId, page, size);
    }

    @GetMapping("/{id}")
    public GetCommentDto getComment(@RequestParam UUID commentId) {
        return this.commentService.getComment(commentId);
    }

    @PostMapping
    public GetCommentDto addCommentDto(
            @PathVariable UUID videoId,
            @RequestBody @Valid AddCommentDto commentData) {
        return this.commentService.addComment(videoId, commentData);
    }
}