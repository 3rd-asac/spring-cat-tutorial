package com.example.demo.src.cat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchOrderDetailReq {
    private int id;
    private String order_detail_status;
}
