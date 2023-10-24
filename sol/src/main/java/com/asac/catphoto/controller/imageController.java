package com.asac.catphoto.controller;

import com.asac.catphoto.DTO.*;
import com.asac.catphoto.service.CatPhotosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",maxAge=3600)
public class imageController {

    private final CatPhotosService catPhotosService;

    @RequestMapping(value = "/api/v1/images", method = RequestMethod.GET)
    public List<CatImage> getImages(@RequestParam Long imageNumber){
        return catPhotosService.getCatImages(imageNumber);
    }

    @RequestMapping(value = "/api/v1/favourite", method = RequestMethod.POST)
    public FavouriteResDto postFavourite(@RequestBody FavouriteDto favouriteDto){
        return catPhotosService.postCatFavourite(favouriteDto);
    }

    @RequestMapping(value = "/api/v1/favourite", method = RequestMethod.GET)
    public List<FavouriteImageDto> getFavouriteImages(@RequestParam Long page, @RequestParam String limit, @RequestParam String sub_id){
        FavouriteImageReqDto favouriteImageReqDto = FavouriteImageReqDto.builder()
                .page(0L)
                .limit("100")
                .sub_id("user-1").build();
        return catPhotosService.getCatFavouriteImages(favouriteImageReqDto);
    }

    @RequestMapping(value = "/api/v1/favourite", method = RequestMethod.DELETE)
    public String deleteFavourite(@RequestParam String favouriteId){
        return catPhotosService.deleteFavourite(favouriteId);
    }

}
