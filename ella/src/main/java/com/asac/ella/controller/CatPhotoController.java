package com.asac.ella.controller;

import com.asac.ella.dto.CatPhotoDto;
import com.asac.ella.dto.FavouriteDto;
import com.asac.ella.service.CatPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class CatPhotoController {

    private final CatPhotoService catPhotoService;

    @GetMapping("/")
    public String catPhotos() {
        return "Catphotos";
    }

    @GetMapping("/photos")
    public String getPhotos(@RequestParam Long numberOfPhotos, Model model) {
        List<CatPhotoDto> catPhotos = catPhotoService.getCatPhotos(numberOfPhotos);
        model.addAttribute("CatPhotoDto", catPhotos);
        return "Catphotos";
    }

    @PostMapping ("/favourites")
    public String saveCatCollection(@RequestParam(value = "checkedValues") String[] checkedValues) {
        for(String str : checkedValues) {
            catPhotoService.saveCollectionList(str);
        }
        return "redirect:/favourites";
    }

    @GetMapping ("/favourites")
    public String getCatCollection(Model model) {
        List<FavouriteDto> favourites = catPhotoService.getCollectionList();
        model.addAttribute("FavouriteDto", favourites);
        return "Collection";
    }

    @DeleteMapping ("/favourites")
    public String deleteCatCollection(@RequestParam(value = "checkedValues") String[] checkedValues) {
        for(String str : checkedValues) {
            catPhotoService.deleteCollectionList(str);
        }
        return "redirect:/favourites";
    }
}
