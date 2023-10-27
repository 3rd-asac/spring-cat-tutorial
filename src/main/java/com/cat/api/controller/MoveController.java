package com.cat.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/View")
public class MoveController {
    @GetMapping("/FavoriteCat")
    public String showCatFavorites(){
        return "favoritesCat";
    }
}
