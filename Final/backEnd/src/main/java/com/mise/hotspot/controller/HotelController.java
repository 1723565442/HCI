package com.mise.hotspot.controller;
import com.mise.hotspot.incantation.CommonResponse;
import com.mise.hotspot.pojo.vo.hotel.GetDetailRequest;
import com.mise.hotspot.pojo.vo.hotel.GetRecommendRequest;
import com.mise.hotspot.pojo.vo.hotel.GetSearchRequest;
import com.mise.hotspot.pojo.vo.hotel.updateRemainsRequest;
import com.mise.hotspot.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("hotelDetail")
    public CommonResponse<?> getHotelDetail(@Valid @RequestBody GetDetailRequest request) {
        return CommonResponse.success(hotelService.getHotelDetail(request.getName()));
    }

    @PostMapping("hotels")
    public CommonResponse<?> getRecommendHotels(@Valid @RequestBody GetRecommendRequest request){
        return CommonResponse.success(hotelService.getRecommend(request.getCity()));
    }

    @PostMapping("search")
    public CommonResponse<?> getSearchHotels(@Valid @RequestBody GetSearchRequest request){
        return CommonResponse.success(hotelService.listHotels(request.getCity(), request.getCheckIn(), request.getCheckOut()));
    }

    @PostMapping("updateRemains")
    public CommonResponse<?> payOrder(@Valid @RequestBody updateRemainsRequest request) {
        return CommonResponse.success(hotelService.updateRemains(request.getName(), request.getRemains()));
    }

}
