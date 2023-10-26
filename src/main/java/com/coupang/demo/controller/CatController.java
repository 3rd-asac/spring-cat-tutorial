package com.coupang.demo.controller;

import com.coupang.demo.DTO.*;
import com.coupang.demo.service.CatImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatImageService catImageService;

    @GetMapping("/api/v1/images")
    public List<CatImage> getImages(@RequestParam Long imageCount){
        return catImageService.getCatImages(imageCount);
    }

    @PostMapping("/api/v1/favourites")
    public FavouriteResDto postFavourite(@RequestBody FavouriteDto favouriteDto){
        return catImageService.postCatFavourite(favouriteDto);
    }

    @GetMapping("/api/v1/favourites")
    public List<FavouriteImageDto> getFavouriteImages(@RequestParam Long page, @RequestParam String limit, @RequestParam String sub_id){
        FavouriteImageReqDto favouriteImageReqDto = FavouriteImageReqDto.builder()
                .page(0L)
                .limit(100L)
                .sub_id(sub_id).build();
        return catImageService.getCatFavouriteImages(favouriteImageReqDto);
    }
    @DeleteMapping("/api/v1/favourites")
    public String deleteFavourite(@RequestParam String favouriteId){
        return catImageService.deleteFavourite(favouriteId);
    }
}