package com.asac.catapiapp.controller;

import com.asac.catapiapp.model.CatResponse;
import com.asac.catapiapp.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/Image/cat")
    public String getRandomCat(@RequestParam(value = "limit", defaultValue = "1") String limit, Model model) {
        System.out.println(limit);
        CatResponse[] catResponses = catService.getRandomCatImage(limit);
        for(CatResponse c : catResponses)
            System.out.println(c.getUrl());
        model.addAttribute("catImages", catResponses);
        return "index";
    }
}
