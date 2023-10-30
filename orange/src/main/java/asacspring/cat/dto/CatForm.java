package asacspring.cat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Getter
@Setter
@Data
public class CatForm {
    private String count;
    private List<String> imageUrls;
}
