package com.chornobuk.practice8.celebapi.controllers;

import com.chornobuk.practice8.celebapi.services.DataUploadingService;
import com.chornobuk.practice8.celebapi.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("celebrities")
public class CelebritiesController {

    private DataUploadingService dataUploadingService;

    @PostMapping("upload")
    @ResponseBody
    public String getDataFile(@RequestParam("file") MultipartFile file) {
        dataUploadingService.saveUploadedData(file);
        return "file uploaded";
    }

    @GetMapping("popularity")
    public List<String> getMostPopularByName(@RequestParam int topSize) {
        return null;
    }

    @GetMapping("search")
    public List<String> findByName(@RequestParam String name, @RequestParam String surname, @RequestParam String middleName) {
        return null;
    }
}
