package com.asac.catphoto.service;

import com.asac.catphoto.DTO.*;

import java.util.List;

public interface CatPhoto {

    public List<CatImage> getCatImages(Long imageNumber);

    public FavouriteResDto postCatFavourite(FavouriteDto favouriteDto);

    public List<FavouriteImageDto> getCatFavouriteImages(FavouriteImageReqDto favouriteImageReqDto);

    public String deleteFavourite(String favourteId);

}
