package com.chornobuk.practice8.celebapi.services.imp;

import com.chornobuk.practice8.celebapi.services.ArchiveService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ZipArchiveService implements ArchiveService {

    @Override
    public void unzip(String pathToFle) {
        File archive = new File(pathToFle);
        File destinationDirectory = archive.getParentFile();
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archive))) {
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                String fileName = entry.getName();
                File newFile = new File(destinationDirectory.getPath() + File.separator + fileName);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                entry = zis.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
