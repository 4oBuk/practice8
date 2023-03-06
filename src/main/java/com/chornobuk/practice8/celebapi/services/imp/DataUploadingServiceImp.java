package com.chornobuk.practice8.celebapi.services.imp;

import com.chornobuk.practice8.celebapi.services.ArchiveService;
import com.chornobuk.practice8.celebapi.services.DataUploadingService;
import com.chornobuk.practice8.celebapi.services.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class DataUploadingServiceImp implements DataUploadingService {

    private final ArchiveService archiveService;
    private final StorageService storageService;

    private final CelebrityParsingService parser;


    public DataUploadingServiceImp(ArchiveService archiveService, StorageService storageService, CelebrityParsingService parser) {
        this.archiveService = archiveService;
        this.storageService = storageService;
        this.parser = parser;
    }

    @Override
    public void saveUploadedData(MultipartFile file) {
        Path path = storageService.store(file);
        Path json = archiveService.unzip(path.toString());
        parser.writeFirmJsonToDB(json.toString());
    }
}
