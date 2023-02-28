package com.chornobuk.practice8.celebapi.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    void deleteAll();
    void store (MultipartFile file);

}
