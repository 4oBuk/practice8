package com.chornobuk.practice8.celebapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();
    void deleteAll();
    Path store (MultipartFile file);

}
