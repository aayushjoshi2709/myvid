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
import com.github.aayushjoshi2709.myvid.ioservice.entity.Comment.CommentBuilder;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;
import com.github.aayushjoshi2709.myvid.ioservice.mapper.comment.GetCommentMapper;
import com.github.aayushjoshi2709.myvid.ioservice.repository.CommentRepository;
import com.github.aayushjoshi2709.myvid.ioservice.service.CommentService;
import com.github.aayushjoshi2709.myvid.ioservice.service.UserService;
import com.github.aayushjoshi2709.myvid.ioservice.service.VideoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final VideoService videoService;
    private final CommentRepository commentRepository;
    private final GetCommentMapper getCommentMapper;

    public Comment getCommentById(UUID commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                () -> {
                    log.error("Comment with comment id: " + commentId + "not found");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Comment with comment id: " + commentId + "not found");
                });
        log.info("Got comment by id: {} comment: ", commentId, comment);
        return comment;
    }

    public GetCommentDto getComment(UUID commentId) {
        log.info("Going to get comment by id: {}", commentId);
        Comment comment = this.getCommentById(commentId);
        return this.getCommentMapper.toDto(comment);
    }

    public List<GetCommentDto> getComments(
            UUID videoId,
            UUID parentCommentId,
            Integer page,
            Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Video video = this.videoService.findVideoObjectById(videoId);
        Page<Comment> comments = this.commentRepository.findAllByVideoAndParentCommentId(video, parentCommentId,
                pageable);
        return comments.stream().map(this.getCommentMapper::toDto).toList();
    }

    public GetCommentDto addComment(UUID videoId, AddCommentDto commentData) {
        User currentUser = this.userService.getCurrentUserDetails();
        Video video = this.videoService.findVideoObjectById(videoId);
        log.info("Going to add the a new comment with message: {} with userId: {} to videoId: {}",
                commentData.getMessage(), currentUser.getId(), video.getId());
        CommentBuilder commentBuilder = Comment.builder()
                .author(currentUser)
                .video(video)
                .message(commentData.getMessage());

        Comment parentComment = null;
        if (commentData.getParentCommentId() != null) {
            parentComment = this.getCommentById(commentData.getParentCommentId());
            commentBuilder.parentComment(parentComment);
        }
        Comment comment = commentBuilder.build();
        Comment savedComment = this.commentRepository.save(comment);
        if (parentComment != null) {
            parentComment.setReplyCount(parentComment.getReplyCount() + 1);
            this.commentRepository.save(parentComment);
        }
        return this.getCommentMapper.toDto(savedComment);
    }
}
