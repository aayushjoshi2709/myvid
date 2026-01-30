package com.github.aayushjoshi2709.myvid.ioservice.service;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.AddCommentDto;
import com.github.aayushjoshi2709.myvid.ioservice.dto.comment.GetCommentDto;

public interface CommentService {
    public GetCommentDto getComment(UUID commentId);

    public List<GetCommentDto> getComments(UUID videoID, UUID parentCommentId, Integer page, Integer size);

    public GetCommentDto addComment(UUID videoId, AddCommentDto commentData);
}
