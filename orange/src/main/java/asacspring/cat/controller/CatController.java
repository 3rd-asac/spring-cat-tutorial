package asacspring.cat.controller;

import asacspring.cat.dto.CatDto;
import asacspring.cat.dto.CatForm;
import asacspring.cat.entity.Cat;
import asacspring.cat.service.CatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class CatController {
    private final CatService catService;

    @GetMapping("/cat")
    public String getOne(Model model) {
       return "catPhotos";

    }
    @PostMapping("/cat")
    public String getCat(Model model, @RequestParam("count") String count) throws JsonProcessingException {
            // 고양이 url 개수만큼 받아오기
            List<String> imageUrls = catService.getCat(Long.getLong(count));
            model.addAttribute("imageUrls", imageUrls);
            model.addAttribute("count", count);
            log.info("count는 ", count);
            return "catPhotos";

    }


}