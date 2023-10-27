package com.asac.catapiapp.controller;

import com.asac.catapiapp.model.CatResponse;
import com.asac.catapiapp.model.FavouritesResponse;
import com.asac.catapiapp.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService){
        this.catService = catService;
    }

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/Image/cat/{limit}")
    public String getRandomCat(@PathVariable(value = "limit") String limit, Model model) {
        System.out.println(limit);
        CatResponse[] catResponses = catService.getRandomCatImage(limit);
        for(CatResponse c : catResponses)
            System.out.println(c.getUrl());
        model.addAttribute("catImages", catResponses);
        return "index";
    }

    @PostMapping("/collection/cat/{sub_id}")
    public String saveCat(Model model, @RequestParam("image_id") String[] ids, @PathVariable(value = "sub_id") String sub_id){
        FavouritesResponse cid = new FavouritesResponse();
        for(String s : ids){
            cid = catService.saveCatImage(s, sub_id);
            System.out.println(cid.getId() + "cid");
        }
        model.addAttribute("favourites_id", cid.getId());
        return "index";
    }

    @GetMapping("/Favourites/{sub_id}")
    public String getFavouritesCat(@PathVariable(value = "sub_id") String sub_id, Model model){
        FavouritesResponse[] favouritesResponse = catService.getFavourtiesImages(sub_id);

        if(favouritesResponse == null) {
            model.addAttribute("message", "해당 아이디의 이미지가 없습니다.");
            return "error";
        }

        System.out.println(favouritesResponse[0].getImage().getUrl());
        model.addAttribute("favouritesResponse", favouritesResponse);
        return "favourites";
    }
}
