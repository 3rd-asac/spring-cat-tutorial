package com.coupang.demo.controller;

import com.coupang.demo.DTO.*;
import com.coupang.demo.service.CatPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatPhotoService catPhotoService;

    @GetMapping("/api/v1/images")
    public List<CatImage> getImages(@RequestParam Long imageNumber){
        return catPhotoService.getCatImages(imageNumber);
    }

    @PostMapping("/api/v1/favourite")
    public FavouriteResDto postFavourite(@RequestBody FavouriteDto favouriteDto){
        return catPhotoService.postCatFavourite(favouriteDto);
    }

    @GetMapping("/api/v1/favourite")
    public List<FavouriteImageDto> getFavouriteImages(@RequestParam Long page, @RequestParam String limit, @RequestParam String sub_id){
        FavouriteImageReqDto favouriteImageReqDto = FavouriteImageReqDto.builder()
                .page(0L)
                .limit("100")
                .sub_id("user-1").build();
        return catPhotoService.getCatFavouriteImages(favouriteImageReqDto);
    }
    @DeleteMapping("/api/v1/favourite")
    public String deleteFavourite(@RequestParam String favouriteId){
        return catPhotoService.deleteFavourite(favouriteId);
    }
}