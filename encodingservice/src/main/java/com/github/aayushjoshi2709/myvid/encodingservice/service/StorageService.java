package com.github.aayushjoshi2709.myvid.encodingservice.service;

public interface StorageService {

    void getFileFromStorage(String fileUrl, String downloadPath);

    void writeFileToStorage(String uploadPath);
}
