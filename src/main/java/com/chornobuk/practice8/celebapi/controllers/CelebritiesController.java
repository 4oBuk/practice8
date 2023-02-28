package com.chornobuk.practice8.celebapi.controllers;

import com.chornobuk.practice8.celebapi.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("celebrities")
public class CelebritiesController {

    private StorageService storageService;

    @PostMapping("upload")
    @ResponseBody
    public String getDataFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
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
