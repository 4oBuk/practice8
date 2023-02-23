package com.chornobuk.practice8.celebapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("celebrities")
public class CelebritiesController {

    @PostMapping()
    public void getDataFile() {

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
