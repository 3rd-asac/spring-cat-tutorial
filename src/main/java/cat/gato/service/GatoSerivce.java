package cat.gato.service;

import cat.gato.domain.CatImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GatoSerivce {

    private static final String CAT_FAVORITE_URL = "https://api.thecatapi.com/v1/favourites";
    private static final String CAT_FAVORITE_DELETE_URL = "https://api.thecatapi.com/v1/favourites/";

    private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";
    @Value("${thecatapi.api-key}")
    private String apiKey;


    private final RestTemplate restTemplate = new RestTemplate();

    public List<CatImage> getRandomCatImages(int limit) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        ResponseEntity<CatImage[]> response = restTemplate.exchange(
                CAT_API_URL + "?limit=" + limit,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CatImage[].class
        );

        return Arrays.asList(response.getBody());
    }


    public void addFavorite(String imageId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("image_id", imageId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(CAT_FAVORITE_URL, request, String.class);
    }

    public List<CatImage> getFavorites() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        ResponseEntity<CatImage[]> response = restTemplate.exchange(
                CAT_FAVORITE_URL,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CatImage[].class
        );

        return Arrays.asList(response.getBody());
    }

    public void deleteFavorite(String favoriteId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        restTemplate.exchange(
                CAT_FAVORITE_DELETE_URL + favoriteId,
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Void.class
        );
    }
}
