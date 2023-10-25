package com.coupang.demo.service;

import com.coupang.demo.DTO.*;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CatService {
    public List<CatImage> getCatImages(Long imageNumber);

    public FavouriteResDto postCatFavourite(FavouriteDto favouriteDto);

    public List<FavouriteImageDto> getCatFavouriteImages(FavouriteImageReqDto favouriteImageReqDto);

    public String deleteFavourite(String favourteId);

}
