package com.example.demo.src.cat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCatReq {
    private String img_id;
    private String url;
}
