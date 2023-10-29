package com.example.demo.src.cat;


import static com.example.demo.common.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.common.BaseResponseStatus.DELETE_FAIL_USERNAME;

import com.example.demo.common.BaseException;
import com.example.demo.src.cat.model.GetCatRes;
import com.example.demo.src.cat.model.PostCatReq;
import com.example.demo.src.cat.model.PostCatRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Service Create, Update, Delete 의 로직 처리
@RequiredArgsConstructor
@Service
public class CatService {

    private final CatDao catDao;


    //POST
    public PostCatRes createCat(PostCatReq postCatReq) throws BaseException {

        try{
            int catId = catDao.createCat(postCatReq); // POINT
            return new PostCatRes(catId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteCat(int catId) throws BaseException {
        try{
            int result = catDao.deleteCat(catId);
            if(result == 0){
                throw new BaseException(DELETE_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCatRes> getCats() throws BaseException{
        try{
            List<GetCatRes> getCatRes = catDao.getCats();
            return getCatRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCatRes> getCatsByImageId(Integer imageId) throws BaseException{
        try{
            List<GetCatRes> getCatsRes = catDao.getCatsByImageId(imageId);
            return getCatsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetCatRes getCat(int CatId) throws BaseException {
        try {
            GetCatRes getCatRes = catDao.getCat(CatId);
            return getCatRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
