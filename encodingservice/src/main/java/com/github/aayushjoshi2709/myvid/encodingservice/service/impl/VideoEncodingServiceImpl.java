package com.github.aayushjoshi2709.myvid.encodingservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.PublishVideoDto;
import com.github.aayushjoshi2709.myvid.encodingservice.dto.enums.VideoStatus;
import com.github.aayushjoshi2709.myvid.encodingservice.service.PubSubService;
import com.github.aayushjoshi2709.myvid.encodingservice.service.StorageService;
import com.github.aayushjoshi2709.myvid.encodingservice.service.VideoEncodingService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.PostConstruct;

import java.nio.file.Files;
import java.nio.file.Path;
import software.amazon.awssdk.services.sqs.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoEncodingServiceImpl implements VideoEncodingService {
    private final StorageService storageService;
    private final PubSubService pubSubService;
    private final ObjectMapper objectMapper;

    @Value("${aws.temp-store.video.src}")
    private String tempSorageVideoSource = "";

    @Value("${aws.temp-store.video.dest}")
    private String tempSorageVideoDestination = "";

    @Value("${aws.endpoint}")
    private String endpointUrl = "";

    @PostConstruct
    public void init() {
        try {
            Path tempSorageVideoSourcePath = Path.of(tempSorageVideoSource);
            Path tempSorageVideoDestinationPath = Path.of(tempSorageVideoDestination);
            Files.createDirectories(tempSorageVideoDestinationPath);
            Files.createDirectories(tempSorageVideoSourcePath);
        } catch (IOException e) {
            log.error("Error trying to create temp directories: ", e);
        }
    }

    private String runAdaptiveBitrateEncoding(String inputPath, String outputDir) {
        final String masterFileName = "master.m3u8";

        List<String> command = Arrays.asList(
                "ffmpeg",
                "-i", inputPath,

                // 1080p
                "-map", "0:v", "-map", "0:a",
                "-s:v:0", "1920x1080", "-b:v:0", "5000k",

                // 720p
                "-map", "0:v", "-map", "0:a",
                "-s:v:1", "1280x720", "-b:v:1", "2800k",

                // 480p
                "-map", "0:v", "-map", "0:a",
                "-s:v:2", "854x480", "-b:v:2", "1400k",

                "-c:v", "libx264",
                "-profile:v", "main",
                "-c:a", "aac",
                "-ar", "48000",

                "-f", "hls",
                "-hls_time", "2",
                "-hls_playlist_type", "vod",
                "-hls_segment_filename", outputDir + "/output_%v/segment_%03d.ts",
                "-master_pl_name", masterFileName,
                outputDir + "/output_%v/playlist.m3u8");

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);

        try {
            Process process = builder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Adaptive bitrate encoding completed successfully.");
            } else {
                System.err.println("FFmpeg failed with exit code: " + exitCode);
            }
            return masterFileName;
        } catch (IOException | InterruptedException e) {
            log.error("Error transcoding file path: {} with stack trace: {}", inputPath, e);
        }
        return masterFileName;
    }

    public void publishEncodedVideoEvent(PublishVideoDto videoDetails) {
        try {
            this.pubSubService.sendMessage(objectMapper.writeValueAsString(videoDetails));
        } catch (JsonProcessingException e) {
            log.error("Failed to publish encoded vedio processed event for id:{} \n error:{}", videoDetails.getId(),
                    e);
            e.printStackTrace();
        }
    }

    @Override
    public void encodeVideo(PublishVideoDto videoDetails) {
        try {
            String originalFileUrl = tempSorageVideoSource + videoDetails.getId();
            this.storageService.getFileByUrl(videoDetails.getVideoUrl(), originalFileUrl);
            String destinationFolderUrl = tempSorageVideoDestination + videoDetails.getId();
            String masterFileName = runAdaptiveBitrateEncoding(originalFileUrl, destinationFolderUrl);
            log.info("Going to upload transcoded file in: {} to s3", destinationFolderUrl);
            String pathUploaded = this.storageService.uploadFolder(videoDetails.getId().toString(),
                    destinationFolderUrl);
            log.info("Successfully uploaded the transcoded file with path: {} to s3 at path: {}", destinationFolderUrl,
                    pathUploaded);

            videoDetails.setVideoUrl(String.format(
                    "%s/%s/%s",
                    this.endpointUrl,
                    pathUploaded,
                    masterFileName));
            videoDetails.setStatus(VideoStatus.PROCESSED);
            log.info("Going to send vedio processed event with data: ", videoDetails);
            this.pubSubService.sendMessage(objectMapper.writeValueAsString(videoDetails));
        } catch (Exception e) {
            log.error("An error occoured while encoding video with  id:{} \n error:{}", videoDetails.getId(), e);
        }
    }

    public void processEncodingVideo() {
        List<Message> videoMessage = this.pubSubService.receiveMessages();
        log.info("Going to process {} messages", videoMessage.size());
        for (Message message : videoMessage) {
            try {
                PublishVideoDto videoDetails = objectMapper.readValue(message.body(), PublishVideoDto.class);
                this.encodeVideo(videoDetails);
                log.info("Going to delete vedio processing event with data: ", videoDetails);
                this.pubSubService.deleteMessage(message);
            } catch (JsonProcessingException e) {
                log.error("Failed to decode json payload inside process encoding vedio for messageId: {} {}",
                        message.messageId(), e);
            }
        }
    }
}
