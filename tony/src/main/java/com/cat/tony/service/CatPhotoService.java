package com.cat.tony.service;

import com.cat.tony.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
//import java.net.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class CatPhotoService {
//    private final
//    private HttpHeaders headers;

    @Value("${catapi.key}")
    private String API_KEY;
    @Value("${catapi.url}")
    private String API_URL;
    @Value("${catapi.favourit.url}")
    private String F_API_URL;
    public List<CatImage> getCatImage() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<CatImage>> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatImage>>() {});
        return response.getBody();
    }

    public List<CatImage> getCatImage(Integer imageNumber) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder
                .fromUriString(API_URL)
                .queryParam("limit", imageNumber).encode().build().toUri();
        ResponseEntity<List<CatImage>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatImage>>() {});
        return response.getBody();
    }

    public FavouriteResDTO postCatFavourite(FavouriteDTO favouriteDTO) {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<FavouriteDTO> requestEntity = RequestEntity
                .post(F_API_URL)
                .header("x-api-key",API_KEY)
                .body(favouriteDTO);

        ResponseEntity<FavouriteResDTO> responseEntity = restTemplate.exchange(
                requestEntity,
                FavouriteResDTO.class
        );

        return responseEntity.getBody();
    }

    public List<FavouriteSearchResDTO> getCatFavourite(FavouriteSearchDTO favouriteSearchDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder
                .fromUriString(F_API_URL)
                .queryParam("page", favouriteSearchDTO.getPage() == null ? 0L : favouriteSearchDTO.getPage())
                .queryParam("limit", favouriteSearchDTO.getLimit() == null ? "100" : favouriteSearchDTO.getLimit())
                .queryParam("sub_id", favouriteSearchDTO.getSub_id() == null ? "tony" : favouriteSearchDTO.getSub_id())
                .queryParam("order", favouriteSearchDTO.getOrder() == null ? "ASC" : favouriteSearchDTO.getOrder())
                .encode().build().toUri();

        ResponseEntity<List<FavouriteSearchResDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<FavouriteSearchResDTO>>() {});

        return response.getBody();
    }

    public String deleteFavourite(String favourteId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder
                .fromUriString(F_API_URL)
                .path("/"+ favourteId)
                .encode()
                .build()
                .toUri();
        ResponseEntity<Object> responseEntity = restTemplate.exchange(uri,
                HttpMethod.DELETE, entity, new ParameterizedTypeReference<Object>() {
                });
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return "delete";
        }
        else{
            return "prodlem";
        }
    }
}
