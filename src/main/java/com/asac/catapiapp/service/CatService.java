package com.asac.catapiapp.service;

import com.asac.catapiapp.model.CatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {

    @Value("${catapi.key}")
    private String API_KEY;

    private final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";

    public String getRandomCatImage() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<CatResponse[]> response = restTemplate.exchange(CAT_API_URL, HttpMethod.GET, entity, CatResponse[].class);
        CatResponse[] catResponses = response.getBody();

        // 예외 처리를 추가로 NullPointerException 방지
        if(catResponses != null && catResponses.length > 0) {
            return catResponses[0].getUrl();
        }

        return null; // 또는 기본 이미지 URL을 반환
    }
}
