package com.example.demo.src.cat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int id;
    private String email;
    private String password;
    private String name;
}
