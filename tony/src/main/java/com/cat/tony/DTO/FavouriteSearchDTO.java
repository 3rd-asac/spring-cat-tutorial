package com.cat.tony.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteSearchDTO{

    private Long page;
    private String limit;
    private String order;
    private String image_id; // image id
    private String sub_id; // user id

}
