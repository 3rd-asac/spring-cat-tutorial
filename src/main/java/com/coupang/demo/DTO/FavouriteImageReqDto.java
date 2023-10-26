package com.coupang.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FavouriteImageReqDto {
    private Long page;
    private Long limit;
    private String sub_id;
}
