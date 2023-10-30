package com.asac.ella.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteDto {
    private String id;
    private String user_id;
    private String image_id;
    private String sub_id;
    private CatPhotoDto image;
}
