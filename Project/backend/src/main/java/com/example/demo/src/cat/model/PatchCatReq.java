package com.example.demo.src.cat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchCatReq {
    private int id;
    private String cat_status;
}
