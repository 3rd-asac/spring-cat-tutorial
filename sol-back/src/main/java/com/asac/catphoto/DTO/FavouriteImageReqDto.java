package com.asac.catphoto.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FavouriteImageReqDto {
    private Long page;
    private String limit;
    private String sub_id;
}
