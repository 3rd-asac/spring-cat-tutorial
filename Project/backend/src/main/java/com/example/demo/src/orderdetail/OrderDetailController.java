package com.example.demo.src.orderdetail;

import static com.example.demo.common.BaseResponseStatus.POST_USERS_EMPTY_EMAIL;
import static com.example.demo.common.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

import com.example.demo.src.orderdetail.OrderDetailService;
import com.example.demo.src.user.model.PostUserRes;
import lombok.RequiredArgsConstructor;
import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.orderdetail.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/orderdetails")
public class OrderDetailController {


    private final OrderDetailService orderDetailService;


    /**
     * 상품 상세 추가 API
     * [POST] /app/orderdetails
     * @return BaseResponse<PostOrderDetailRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostOrderDetailRes> createOrderDetail(@RequestBody PostOrderDetailReq postOrderDetailReq) {
        try{
            PostOrderDetailRes postOrderDetailRes = orderDetailService.createOrderDetail(postOrderDetailReq);
            return new BaseResponse<>(postOrderDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 상세 조회 API
     * [GET] /app/orderdetails
     * 상품 상세 번호 검색 조회 API
     * [GET] /app/orderdetails? orderId=
     * @return BaseResponse<List<GetOrderDetailRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:8080/app/orderdetails
    public BaseResponse<List<GetOrderDetailRes>> getOrderDetails(@RequestParam(required = false) Integer orderId) {
        try{
            if(orderId == null){
                List<GetOrderDetailRes> getOrderDetailsRes = orderDetailService.getOrderDetails();
                return new BaseResponse<>(getOrderDetailsRes);
            }
            // Get OrderDetails
            List<GetOrderDetailRes> getOrderDetailsRes = orderDetailService.getOrderDetailsByOrderId(orderId);
            return new BaseResponse<>(getOrderDetailsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 상세 1개 조회 API
     * [GET] /app/orderdetails/:orderdetailId
     * @return BaseResponse<GetOrderDetailRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{orderDetailId}") // (GET) 127.0.0.1:9000/app/orderdetails/:orderdetailId
    public BaseResponse<GetOrderDetailRes> getOrderDetail(@PathVariable("orderDetailId") int orderDetailId) {
        // Get OrderDetails
        try{
            GetOrderDetailRes getOrderDetailRes = orderDetailService.getOrderDetail(orderDetailId);
            return new BaseResponse<>(getOrderDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }



    /**
     * 유저정보변경 API
     * [PATCH] /app/orderdetails/:orderdetailId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{orderdetailId}")
    public BaseResponse<String> modifyOrderDetailName(@PathVariable("orderdetailId") int orderdetailId, @RequestBody OrderDetail orderDetail){
        try {
            PatchOrderDetailReq patchOrderDetailReq = new PatchOrderDetailReq(orderdetailId, orderDetail.getOrder_detail_status());
            orderDetailService.modifyOrderDetailName(patchOrderDetailReq);

            String result = "";
        return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 유저정보삭제 API
     * [DELETE] /app/orderdetails/:orderdetailId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/{orderDetailId}")
    public BaseResponse<String> deleteOrderDetail(@PathVariable("orderDetailId") int orderDetailId){
        try {
            orderDetailService.deleteOrderDetail(orderDetailId);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
