package com.mise.hotspot.controller;

import com.mise.hotspot.incantation.CommonResponse;
import com.mise.hotspot.service.CityService;
import com.mise.hotspot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;


    @GetMapping("getallcitys")
    public CommonResponse<?> getAllCitys(){
        return CommonResponse.success(cityService.getCity());
    }
}
