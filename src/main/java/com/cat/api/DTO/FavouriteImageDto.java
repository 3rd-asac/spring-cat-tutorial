package com.cat.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteImageDto {

    private String id;
    private String image_id;
    private String sub_id;
    private String created_at;
    private ImageObject image;

    @Getter
    @Setter
    public static class ImageObject{
        private String id;
        private String url;
    }
}