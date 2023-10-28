package asacspring.cat.dto;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class CatDto {
    String id;
    String url;
    Long width;
    Long height;
}
