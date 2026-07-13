package com.github.aayushjoshi2709.commentservice.service;

import com.github.aayushjoshi2709.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
}
