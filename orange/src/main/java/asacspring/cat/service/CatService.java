package asacspring.cat.service;

import asacspring.cat.dto.CatDto;
import asacspring.cat.repository.CatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatService {
    private final RestTemplate restTemplate;
    private final CatRepository catRepository;

    /**
     * 고양이 사진 1개를 전송
     */
    public List<String> getCat(Long count) throws JsonProcessingException {
        String apiUrl = "https://api.thecatapi.com/v1/images/search";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // 반환할 이미지 url 리스트
        List<String> imageUrls = new ArrayList<>();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // 이미지 개수가 1개이거나 입력되지 않은 경우
            if (count==null){
                String jsonString = responseEntity.getBody();

                // 성공적으로 데이터를 가져왔을 때
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> catList = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
                // catList의 첫 번째 항목을 가져와서 출력

                Map<String, Object> catInfo = catList.get(0);
                System.out.println("ID: " + catInfo.get("id"));
                System.out.println("URL: " + catInfo.get("url"));
                System.out.println("Width: " + catInfo.get("width"));
                System.out.println("Height: " + catInfo.get("height"));
                imageUrls.add(catInfo.get("url").toString());
                return imageUrls;
            }
            // 이미지 개수가 여러 개인 경우
            else{
                String jsonString = responseEntity.getBody();

                // 성공적으로 데이터를 가져왔을 때
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> catList = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
                // catList의 첫 번째 항목을 가져와서 출력
                for(int i=0;i<count;i++){
                    Map<String, Object> catInfo = catList.get(i);
                    imageUrls.add(catInfo.get("url").toString());
                }
                return imageUrls;
            }


        } else {
            // 오류 처리
            return null;
        }
    }
}
