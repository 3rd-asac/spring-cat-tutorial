package cat.gato.controller;

import cat.gato.domain.CatImage;
import cat.gato.service.GatoService; // 오타 수정
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cats")
@RequiredArgsConstructor
public class GatoController {

    private final GatoService gatoService;

    @GetMapping
    public String getRandomCatImages(Model model, @RequestParam(defaultValue = "1") int limit) {
        List<CatImage> catImages = gatoService.getRandomCatImages(limit);
        model.addAttribute("catImages", catImages);
        return "cats";
    }

    @PostMapping("/favorite")
    public String addFavorite(@RequestParam String imageId) {
        gatoService.addFavorite(imageId);
        return "redirect:/cats/favorites";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        List<CatImage> favorites = gatoService.getFavorites();
        model.addAttribute("favorites", favorites);
        return "favorites";
    }

    @PostMapping("/favorites/delete")
    public String deleteFavorite(@RequestParam String favoriteId) {
        gatoService.deleteFavorite(favoriteId);
        return "redirect:/cats/favorites";
    }
}
