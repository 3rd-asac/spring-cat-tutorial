package com.asac.catapiapp.service;

import com.asac.catapiapp.constant.api.params.Cat_Api_Param;
import com.asac.catapiapp.constant.api.path.Cat_Api_Path;
import com.asac.catapiapp.model.CatResponse;
import com.asac.catapiapp.model.FavouritesRequest;
import com.asac.catapiapp.model.FavouritesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatService {

    @Value("${api.key}")
    private String API_KEY;

    public CatResponse[] getRandomCatImage(String limit) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(Cat_Api_Path.BASEURI)
                .path(Cat_Api_Path.CATIMAGE)
                .queryParam(Cat_Api_Param.LIMIT, limit)  // query parameter가 필요한 경우 이와 같이 사용
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        headers.set("Content-Type", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<CatResponse[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CatResponse[].class);
        CatResponse[] catResponses = response.getBody();

        // 예외 처리를 추가로 NullPointerException 방지
        if(catResponses != null && catResponses.length > 0) {
            return catResponses;
        }

        return null; // 또는 기본 이미지 URL을 반환
    }

    public FavouritesResponse saveCatImage(String ids, String sub_id){
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(Cat_Api_Path.BASEURI)
                .path(Cat_Api_Path.FAVOURITES)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        FavouritesRequest favouritesRequest = new FavouritesRequest();
        favouritesRequest.setImage_id(ids);
        favouritesRequest.setSub_id(sub_id);

        RequestEntity<FavouritesRequest> request = RequestEntity
                .post(uri)
                .header("x-api-key", API_KEY)
                .body(favouritesRequest);

        ResponseEntity<FavouritesResponse> response = restTemplate.exchange(request, FavouritesResponse.class);
        FavouritesResponse favouritesResponse = response.getBody();

        return favouritesResponse;
    }

    public FavouritesResponse[] getFavourtiesImages(String sub_id){
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(Cat_Api_Path.BASEURI)
                .path(Cat_Api_Path.FAVOURITES)
                .queryParam(Cat_Api_Param.SUB_ID, sub_id)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        headers.set("Content-Type", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<FavouritesResponse[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, FavouritesResponse[].class);
        FavouritesResponse[] favouritesResponse = response.getBody();

        if(favouritesResponse == null || favouritesResponse.length == 0){
            return null;
        }

        return favouritesResponse;
    }
}
