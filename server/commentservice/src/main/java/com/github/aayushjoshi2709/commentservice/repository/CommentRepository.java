package com.github.aayushjoshi2709.commentservice.repository;

import com.github.aayushjoshi2709.commentservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<User, UUID> {
}
