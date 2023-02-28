package com.chornobuk.practice8.celebapi.services.imp;

import com.chornobuk.practice8.celebapi.services.ArchiveService;
import com.chornobuk.practice8.celebapi.services.DataUploadingService;
import com.chornobuk.practice8.celebapi.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class DataUploadingServiceImp implements DataUploadingService {

    private final ArchiveService archiveService;
    private final StorageService storageService;

    private final String pathToStorage;

    public DataUploadingServiceImp(ArchiveService archiveService, StorageService storageService, @Value("${storage.path}") String pathToStorage) {
        this.archiveService = archiveService;
        this.storageService = storageService;
        this.pathToStorage = pathToStorage;
    }

    @Override
    public void saveUploadedData(MultipartFile file) {
        Path path = storageService.store(file);
        archiveService.unzip(path.toString());
    }
}
