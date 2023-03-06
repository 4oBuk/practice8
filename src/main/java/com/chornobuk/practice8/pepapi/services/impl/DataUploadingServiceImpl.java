package com.chornobuk.practice8.pepapi.services.impl;

import com.chornobuk.practice8.pepapi.services.ArchiveService;
import com.chornobuk.practice8.pepapi.services.DataUploadingService;
import com.chornobuk.practice8.pepapi.services.PepParsingService;
import com.chornobuk.practice8.pepapi.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@AllArgsConstructor
public class DataUploadingServiceImpl implements DataUploadingService {

    private final ArchiveService archiveService;
    private final StorageService storageService;
    private final PepParsingService parser;


    @Override
    public void saveUploadedData(MultipartFile file) {
        Path path = storageService.store(file);
        Path json = archiveService.unzip(path.toString());
        parser.writeJsonFileToDB(json.toString());
    }
}
