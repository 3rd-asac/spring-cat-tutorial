package com.cat.tony.controller;

import com.cat.tony.DTO.*;
import com.cat.tony.service.CatPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class GetPhoto {

    @Autowired
    private CatPhotoService catPhotoService;

    @GetMapping(value = "/photo")
    public List<CatImage> getCatRandomImage() {
        return catPhotoService.getCatImage();
    }

    @GetMapping(value = "/photo1")
    public List<CatImage> getCatImage(@RequestParam String limit) {
        return catPhotoService.getCatImage(Integer.parseInt(limit));
    }

    @PostMapping(value = "/favourites")
    public FavouriteResDTO postFavourite(@RequestBody FavouriteDTO favouriteDTO) {
        return catPhotoService.postCatFavourite(favouriteDTO);
    }

    @GetMapping(value = "/favourites")
    public List<FavouriteSearchResDTO> getFavourite(FavouriteSearchDTO favouriteImageReqDto) {
//        FavouriteSearchDTO favouriteImageReqDto = FavouriteSearchDTO.builder()
//                .page(0L)
//                .limit("100")
//                .sub_id("tony")
//                .order("ASC").build();
        return catPhotoService.getCatFavourite(favouriteImageReqDto);
    }

    @DeleteMapping(value = "/favourites/{favouriteId}")
    public String deleteFavourite(@PathVariable String favouriteId) {
        return catPhotoService.deleteFavourite(favouriteId);
    }
}
