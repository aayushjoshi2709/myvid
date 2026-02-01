package com.github.aayushjoshi2709.myvid.encodingservice.service;

public interface StorageService {

    void getFileByKey(String key, String downloadPath);

    void getFileByUrl(String fileUrl, String downloadPath);

    
    String uploadFolder(String prefix, String folderPath);

    void uploadFile(String filePath);
}
