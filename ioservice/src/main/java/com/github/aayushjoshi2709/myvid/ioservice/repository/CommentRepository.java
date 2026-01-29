package com.github.aayushjoshi2709.myvid.ioservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.myvid.ioservice.entity.Comment;
import com.github.aayushjoshi2709.myvid.ioservice.entity.Video;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Page<Comment> findAllByVideo(Video video, Pageable pageable);
}
