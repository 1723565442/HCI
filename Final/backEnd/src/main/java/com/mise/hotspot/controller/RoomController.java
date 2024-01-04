package com.mise.hotspot.controller;
import com.mise.hotspot.incantation.CommonResponse;
import com.mise.hotspot.pojo.vo.hotel.GetDetailRequest;
import com.mise.hotspot.pojo.vo.hotel.GetRecommendRequest;
import com.mise.hotspot.pojo.vo.hotel.GetSearchRequest;
import com.mise.hotspot.pojo.vo.order.PayOrderRequest;
import com.mise.hotspot.pojo.vo.room.GetRoomDetailRequest;
import com.mise.hotspot.service.HotelService;
import com.mise.hotspot.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @PostMapping("roomDetail")
    public CommonResponse<?> getRoomDetail(@Valid @RequestBody GetRoomDetailRequest request) {
        return CommonResponse.success(roomService.getRoomDetail(request.getId()));
    }

}
