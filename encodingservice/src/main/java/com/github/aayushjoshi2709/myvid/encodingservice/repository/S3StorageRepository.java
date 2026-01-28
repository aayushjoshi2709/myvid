package com.github.aayushjoshi2709.myvid.encodingservice.repository;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileDownload;
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.FileDownload;
import software.amazon.awssdk.transfer.s3.model.UploadDirectoryRequest;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

@Repository
@Slf4j
public class S3StorageRepository {
        @Value("${aws.s3.data-processing.bucket-name}")
        private String dataProcessingBucket;

        @Value("${aws.s3.processed-data.bucket-name}")
        private String dataProcessedBucket;

        private final S3TransferManager transferManager;

        public S3StorageRepository(
                        S3TransferManager transferManager) {
                this.transferManager = transferManager;
        }

        public void getFileFromS3(
                        String key,
                        String downloadedFileWithPath) {
                log.info("Going to get key:{} to download path: {}", key, downloadedFileWithPath);
                DownloadFileRequest downloadFileRequest = DownloadFileRequest.builder()
                                .getObjectRequest(b -> b.bucket(dataProcessingBucket).key(key))
                                .destination(Paths.get(downloadedFileWithPath))
                                .build();

                FileDownload downloadFile = transferManager.downloadFile(downloadFileRequest);

                CompletedFileDownload downloadResult = downloadFile.completionFuture().join();
                log.info("Content length [{}]", downloadResult.response().contentLength());
        }

        public String saveFileToS3(String originalUrl) {
                return "";
        }

        public String saveFolderToS3(String prefix, String folderPath) {
                transferManager.uploadDirectory(
                                UploadDirectoryRequest.builder()
                                                .bucket(dataProcessedBucket)
                                                .source(Paths.get(folderPath))
                                                .s3Prefix(prefix)
                                                .build())
                                .completionFuture().join();
                return dataProcessedBucket + "/" + prefix;
        }
}
