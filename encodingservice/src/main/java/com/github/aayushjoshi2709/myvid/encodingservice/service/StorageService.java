package com.github.aayushjoshi2709.myvid.encodingservice.service;

public interface StorageService {

    void getFileFromStorage(String originalUrl, String downloadPath);

    void addFilesToStorage(String uploadPath);

}
