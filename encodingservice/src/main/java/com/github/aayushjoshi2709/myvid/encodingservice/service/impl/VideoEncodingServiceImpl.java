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
                "-map", "0:v:0", "-map", "0:a:0",
                "-s:v:0", "1920x1080", "-c:v:0", "libx264", "-b:v:0", "5000k",
                "-maxrate:v:0", "5350k", "-bufsize:v:0", "7500k",
                "-c:a:0", "aac", "-b:a:0", "128k", "-ar:a:0", "48000",

                // 720p
                "-map", "0:v:0", "-map", "0:a:0",
                "-s:v:1", "1280x720", "-c:v:1", "libx264", "-b:v:1", "2800k",
                "-maxrate:v:1", "2996k", "-bufsize:v:1", "4200k",
                "-c:a:1", "aac", "-b:a:1", "128k", "-ar:a:1", "48000",

                // 480p
                "-map", "0:v:0", "-map", "0:a:0",
                "-s:v:2", "854x480", "-c:v:2", "libx264", "-b:v:2", "1400k",
                "-maxrate:v:2", "1498k", "-bufsize:v:2", "2100k",
                "-c:a:2", "aac", "-b:a:2", "128k", "-ar:a:2", "48000",

                // 360p
                "-map", "0:v:0", "-map", "0:a:0",
                "-s:v:3", "640x360", "-c:v:3", "libx264", "-b:v:3", "800k",
                "-maxrate:v:3", "856k", "-bufsize:v:3", "1200k",
                "-c:a:3", "aac", "-b:a:3", "96k", "-ar:a:3", "48000",

                // HLS settings
                "-f", "hls",
                "-hls_time", "6",
                "-hls_playlist_type", "vod",
                "-hls_flags", "independent_segments",
                "-hls_segment_type", "mpegts",
                "-hls_segment_filename", outputDir + "/output_%v/segment_%03d.ts",
                "-master_pl_name", masterFileName,
                "-var_stream_map", "v:0,a:0 v:1,a:1 v:2,a:2 v:3,a:3",
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
