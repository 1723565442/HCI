package com.mise.hotspot.pojo.vo.order;

import com.mise.hotspot.pojo.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data

/**
 * 订单列表中的数据类型，按照需要设计，在这个页面并不需要订单的所有信息
 * 详细信息在OrderEntity中，用于OrderDetail页面
 */

public class OrderVO {
    private Long id;
    //    private Long userId;
    private String roomNumber;
    private String guestName;
    private Integer price;
    private Integer guestsCount;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private String roomType;

//    private LocalDateTime orderTime;
//    private String phoneNumber;
//    private String orderNote;//备注
}
