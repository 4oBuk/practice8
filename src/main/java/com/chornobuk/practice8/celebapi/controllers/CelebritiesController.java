package com.chornobuk.practice8.celebapi.controllers;

import com.chornobuk.practice8.celebapi.dtos.CelebrityQueryDto;
import com.chornobuk.practice8.celebapi.entities.Celebrity;
import com.chornobuk.practice8.celebapi.services.CelebrityService;
import com.chornobuk.practice8.celebapi.services.DataUploadingService;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("celebrities")
public class CelebritiesController {

    private DataUploadingService dataUploadingService;
    private CelebrityService celebrityService;

    @PostMapping("upload")
    @ResponseBody
    public String getDataFile(@RequestParam("file") MultipartFile file) {
        dataUploadingService.saveUploadedData(file);
        return "file uploaded";
    }

    @GetMapping("popularity")
    public Document getMostPopularByName() {
        return celebrityService.getMostPopularNames();
    }

    @GetMapping("search")
    public Page<Celebrity> findByName(@RequestBody CelebrityQueryDto dto) {
        return celebrityService.getByFullName(dto);
    }
}
