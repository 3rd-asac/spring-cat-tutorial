package asacspring.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home() {
        return "home";
    }


    @RequestMapping("/cat-photos")
    public String catPhotos(Model model) {
        model.addAttribute("num", 1);
        return "catPhotos";
    }

    @RequestMapping("/collection")
    public String collection(){
        return "collection.html";
    }
}
