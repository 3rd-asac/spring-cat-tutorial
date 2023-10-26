package com.asac.catapiapp.service;

import com.asac.catapiapp.model.CatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatService {

    @Value("${api.key}")
    private String API_KEY;

    private final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";

    public CatResponse[] getRandomCatImage(String limit) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.thecatapi.com") //http://localhost에 호출
                .path("/v1/images/search")
                .queryParam("limit", limit)  // query parameter가 필요한 경우 이와 같이 사용
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<CatResponse[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CatResponse[].class);
        CatResponse[] catResponses = response.getBody();

        // 예외 처리를 추가로 NullPointerException 방지
        if(catResponses != null && catResponses.length > 0) {
            return catResponses;
        }

        return null; // 또는 기본 이미지 URL을 반환
    }
}
