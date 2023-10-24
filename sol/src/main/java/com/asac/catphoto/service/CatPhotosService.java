package com.asac.catphoto.service;

import com.asac.catphoto.DTO.*;
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
@RequiredArgsConstructor  // 생성작를 통한 DI
public class CatPhotosService implements CatPhoto {

    // 생성작를 통한 DI
    private final RestTemplate restTemplate;

    @Value("${api.cat.host.url}")
    public String hostUrl=null;
    @Value("${api.cat.xkey}")
    public String xkey=null;

    @Override
    public List<CatImage> getCatImages(Long imageNumber) {
        URI uri = UriComponentsBuilder
                .fromUriString(hostUrl)
                .path("/images/search")
                .queryParam("limit",imageNumber)
                .encode()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",xkey);
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
                .fromUriString(hostUrl)
                .path("/favourites")
                .encode()
                .build()
                .toUri();
        RequestEntity<FavouriteDto> requestEntity = RequestEntity
                .post(uri)
                .header("x-api-key",xkey)
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
                .fromUriString(hostUrl)
                .path("/favourites")
                .queryParam("sub_id",favouriteImageReqDto.getSub_id())
                .queryParam("page",favouriteImageReqDto.getPage())
                .queryParam("limit",favouriteImageReqDto.getLimit())
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",xkey);
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
                .fromUriString(hostUrl)
                .path("/favourites/"+favourteId)
                .encode()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key",xkey);
        HttpEntity entity = new HttpEntity(headers);

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
