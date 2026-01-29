package com.github.aayushjoshi2709.myvid.ioservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.AddCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.GetCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Comment;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.comment.GetCommentMapper;
import com.github.aayushjoshi2709.myvid.ioservice.repository.CommentRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.CommentService;
import com.github.aayushjoshi2709.myvid.ioservice.service.UserService;
import com.github.aayushjoshi2709.myvid.ioservice.service.VideoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final VideoService videoService;
    private final CommentRepository commentRepository;
    private final GetCommentMapper getCommentMapper;

    public GetCommentDto getComment(UUID commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Comment with comment id: " + commentId + "not found"));
        return this.getCommentMapper.toDto(comment);
    }

    public List<GetCommentDto> getComments(
            UUID videoId,
            Integer page,
            Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Video video = this.videoService.findVideoObjectById(videoId);
        Page<Comment> comments = this.commentRepository.findAllByVideo(video, pageable);
        return comments.stream().map(this.getCommentMapper::toDto).toList();
    }

    public GetCommentDto addComment(UUID videoId, AddCommentDto commentData) {
        User currentUser = this.userService.getCurrentUserDetails();
        Video video = this.videoService.findVideoObjectById(videoId);
        Comment comment = Comment.builder()
                .author(currentUser)
                .video(video)
                .message(commentData.getMessage())
                .build();
        Comment savedComment = this.commentRepository.save(comment);
        return this.getCommentMapper.toDto(savedComment);
    }
}
