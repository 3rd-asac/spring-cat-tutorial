package com.coupang.demo.service;

import com.coupang.demo.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatImageService implements CatService {
    private final RestTemplate restTemplate;

    @Value("${external-api.url}")
    private String apiUrl;

    @Value("${external-api.apiKey}")
    private String apiKey;

    @Override
    public List<CatImage> getCatImages(Long imageCount) {
        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/images/search")
                .queryParam("limit",imageCount)
                .encode()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",apiKey);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<CatImage>> responseEntity = restTemplate.exchange(uri,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatImage>>() {
                });
        System.out.println(responseEntity);
        return responseEntity.getBody();
    }

    @Override
    public FavouriteResDto postCatFavourite(FavouriteDto favouriteDto) {
        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/favourites")
                .encode()
                .build()
                .toUri();
        RequestEntity<FavouriteDto> requestEntity = RequestEntity
                .post(uri)
                .header("x-api-key",apiKey)
                .body(favouriteDto);
        ResponseEntity<FavouriteResDto> responseEntity = restTemplate.exchange(
                requestEntity,
                FavouriteResDto.class
        );
        return responseEntity.getBody();
    }
    @Override
    public List<FavouriteImageDto> getCatFavouriteImages(FavouriteImageReqDto favouriteImageReqDto) {
        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/favourites")
                .queryParam("sub_id",favouriteImageReqDto.getSub_id())
                .queryParam("page",favouriteImageReqDto.getPage())
                .queryParam("limit",favouriteImageReqDto.getLimit())
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",apiKey);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<FavouriteImageDto>> responseEntity = restTemplate.exchange(uri,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<FavouriteImageDto>>() {
                });

//        System.out.println(responseEntity);
        return responseEntity.getBody();
    }

    @Override
    public String deleteFavourite(String favourteId) {
        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/favourites/"+favourteId)
                .encode()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",apiKey);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(uri,
                HttpMethod.DELETE, entity, new ParameterizedTypeReference<Object>() {
                });

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return "즐겨찾기 삭제 성공";
        }
        else{
            return "오류 : 서버 내용확인";
        }
    }




}
