package com.github.aayushjoshi2709.myvid.videoservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.myvid.videoservice.entity.Video;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {

    Page<Video> findAll(Pageable pageable);
}
