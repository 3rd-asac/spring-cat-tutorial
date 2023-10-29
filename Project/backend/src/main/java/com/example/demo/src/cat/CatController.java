package com.example.demo.src.cat;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.cat.model.GetCatRes;
import com.example.demo.src.cat.model.PostCatReq;
import com.example.demo.src.cat.model.PostCatRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/cat")
public class CatController {


    private final CatService catService;

    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostCatRes> createCat(@RequestBody PostCatReq postCatReq) {
        try{
            PostCatRes postCatRes = catService.createCat(postCatReq);
            return new BaseResponse<>(postCatRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetCatRes>> getCats(@RequestParam(required = false) Integer catId) {
        try{
            if(catId == null){
                List<GetCatRes> getCatsRes = catService.getCats();
                return new BaseResponse<>(getCatsRes);
            }
            List<GetCatRes> getCatsRes = catService.getCatsByImageId(catId);
            return new BaseResponse<>(getCatsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/{catId}")
    public BaseResponse<GetCatRes> getCat(@PathVariable("catId") int catId) {
        try{
            GetCatRes getCatRes = catService.getCat(catId);
            return new BaseResponse<>(getCatRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @DeleteMapping("/{catId}")
    public BaseResponse<String> deleteCat(@PathVariable("catId") int catId){
        try {
            catService.deleteCat(catId);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
