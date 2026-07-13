package com.github.aayushjoshi2709.commentservice.repository;

import com.github.aayushjoshi2709.commentservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
}
