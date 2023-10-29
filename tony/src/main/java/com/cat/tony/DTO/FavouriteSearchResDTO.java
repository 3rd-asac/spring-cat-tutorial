package com.cat.tony.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteSearchResDTO {
    private String id;
    private String image_id;
    private String sub_id;
    private String created_at;
    private Object image;
}


