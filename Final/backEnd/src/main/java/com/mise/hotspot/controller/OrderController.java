package com.mise.hotspot.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.mise.hotspot.incantation.CommonResponse;
import com.mise.hotspot.pojo.entity.OrderEntity;
import com.mise.hotspot.pojo.vo.hotel.GetDetailRequest;
import com.mise.hotspot.pojo.vo.hotel.GetRecommendRequest;
import com.mise.hotspot.pojo.vo.hotel.GetSearchRequest;
import com.mise.hotspot.pojo.vo.order.*;
import com.mise.hotspot.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("add")
    public CommonResponse<?> addOrder(@Valid @RequestBody AddOrderRequest request) {
        return CommonResponse.success(orderService.addOrder(
                request.getId(),
                request.getHotelName(),
                request.getRoomNumber(),
                request.getGuestName(),
                request.getPrice(),
                request.getGuestsCount(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getPhoneNumber(),
                request.getRoomType(),
                request.getOrderNote(),
                request.getUserId()
        ));
    }
    @PostMapping("delete")
    public CommonResponse<?> deleteOrder(@Valid @RequestBody DeleteOrderRequest request) {
        return CommonResponse.success(orderService.deleteOrder(Long.parseLong(request.getId())));//订单ID
    }

    @PostMapping("pay")
    public CommonResponse<?> payOrder(@Valid @RequestBody PayOrderRequest request) {
        return CommonResponse.success(orderService.payOrder(request.getId(), request.getUserId()));
    }

    @PostMapping("orders")
    public CommonResponse<List<OrderVO>> listOrders(@Valid @RequestBody ListOrderRequest request){
        System.out.println(request);
        return CommonResponse.success(orderService.listOrders(Long.parseLong(request.getId())));//用户Id
    }

    @PostMapping("details")
    public CommonResponse<OrderEntity> detail(@Valid @RequestBody DeleteOrderRequest request) {
        System.out.println("orderID" +  request);
        return CommonResponse.success(orderService.detail(Long.parseLong(request.getId())));
    }

    @PostMapping("cancel")
    public CommonResponse<List<OrderVO>> cancelOrders(@Valid @RequestBody ListOrderRequest request){
        return CommonResponse.success(orderService.cancelOrder(Long.parseLong(request.getId())));//订单ID
    }


}
