package com.chornobuk.practice8.pepapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface DataUploadingService {
    void saveUploadedData(MultipartFile file);
}
