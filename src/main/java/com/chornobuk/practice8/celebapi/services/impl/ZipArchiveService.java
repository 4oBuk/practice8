package com.chornobuk.practice8.celebapi.services.impl;

import com.chornobuk.practice8.celebapi.services.ArchiveService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ZipArchiveService implements ArchiveService {

    @Override
    public Path unzip(String pathToFle) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathToFle))) {
            File destinationDirectory = new File(pathToFle).getParentFile();
            byte[] buffer = new byte[1024];
            ZipEntry entry = zis.getNextEntry();
            String fileName = entry.getName();
            File newFile = new File(destinationDirectory.getPath() + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            return newFile.toPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;//todo: remove it
    }
}
