package com.github.aayushjoshi2709.myvid.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.myvid.server.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {}
