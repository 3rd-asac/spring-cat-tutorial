package com.asac.ella.service;

import com.asac.ella.dto.CatPhotoDto;
import com.asac.ella.dto.FavouriteDto;
import com.asac.ella.dto.FavouriteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CatPhotoService {
    @Value("${BASE-URL}")
    public String baseUrl;

    @Value("${API-KEY}")
    public String apiKey;

    public List<CatPhotoDto> getCatPhotos(Long numberOfPhotos){
        WebClient webclient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

        Mono<List<CatPhotoDto>> response = webclient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/images/search")
                        .queryParam("api_key",apiKey)
                        .queryParam("limit",numberOfPhotos)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CatPhotoDto>>() {});

        List<CatPhotoDto> catPhotos = response.block();
        return catPhotos;
    }

    public String saveCollectionList(String checkedValues){
        FavouriteRequestDto favouriteRequestDto = new FavouriteRequestDto();
        favouriteRequestDto.setImage_id(checkedValues);

        WebClient webclient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

        String response = webclient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/favourites")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-api-key",apiKey)
                .bodyValue(favouriteRequestDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    public List<FavouriteDto> getCollectionList(){
        WebClient webclient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

        Mono<List<FavouriteDto>> response = webclient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/favourites")
                        .queryParam("api_key",apiKey)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FavouriteDto>>() {});

        List<FavouriteDto> favourites = response.block();
        return favourites;
    }

    public String deleteCollectionList(String checkedValue){

        WebClient webclient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

        String response = webclient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/favourites/"+checkedValue)
                        .build())
                .header("x-api-key",apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
}
