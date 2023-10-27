package cat.gato.controller;

import cat.gato.domain.CatImage;
import cat.gato.service.GatoSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cats")
@RequiredArgsConstructor
public class GatoController {

    private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";
    @Value("${thecatapi.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final GatoSerivce gatoService;

    @GetMapping
    public String getRandomCatImages(Model model, @RequestParam(defaultValue = "1") int limit) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        ResponseEntity<CatImage[]> response = restTemplate.exchange(
                CAT_API_URL + "?limit=" + limit,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CatImage[].class
        );

        List<CatImage> catImages = Arrays.asList(response.getBody());
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
