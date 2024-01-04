package com.mise.hotspot.pojo.vo.hotel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class HotelVO {
    private String name;
    private String introduction;
    private String price;
    private String view;

}
