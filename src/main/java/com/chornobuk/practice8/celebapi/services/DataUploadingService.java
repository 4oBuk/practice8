package com.chornobuk.practice8.celebapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface DataUploadingService {
    void saveUploadedData(MultipartFile file);
}
