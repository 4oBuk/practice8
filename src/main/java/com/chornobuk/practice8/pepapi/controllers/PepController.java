package com.chornobuk.practice8.pepapi.controllers;

import com.chornobuk.practice8.pepapi.dtos.PepQueryDto;
import com.chornobuk.practice8.pepapi.entities.Pep;
import com.chornobuk.practice8.pepapi.services.PepService;
import com.chornobuk.practice8.pepapi.services.DataUploadingService;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("pep")
public class PepController {

    private DataUploadingService dataUploadingService;
    private PepService pepService;

    @PostMapping("upload")
    @ResponseBody
    public String getDataFile(@RequestParam("file") MultipartFile file) {
        dataUploadingService.saveUploadedData(file);
        return "file uploaded";
    }

    @GetMapping("popularity")
    public Document getMostPopularByName() {
        return pepService.getMostPopularNames();
    }

    @PostMapping("search")
    public Page<Pep> findByName(@RequestBody PepQueryDto dto) {
        return pepService.getByFullName(dto);
    }
}
