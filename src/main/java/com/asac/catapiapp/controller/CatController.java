package com.asac.catapiapp.controller;

import com.asac.catapiapp.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("/random-cat")
    public ResponseEntity<String> getRandomCat() {
        String imageUrl = catService.getRandomCatImage();
        return ResponseEntity.ok(imageUrl);
    }
}
