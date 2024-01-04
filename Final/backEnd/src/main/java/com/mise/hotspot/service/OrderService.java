package com.mise.hotspot.service;

import com.mise.hotspot.pojo.entity.OrderEntity;
import com.mise.hotspot.pojo.entity.UserEntity;
import com.mise.hotspot.pojo.vo.order.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderEntity addOrder(Long id,
                         String hotelName, String roomNumber, String guestName,
                         Integer price, Integer guestsCount,
                         String checkInDate,String checkOutDate,
                         String phoneNumber,String roomType, String OrderNote, Long userId);

    int cancelOrder(Long id);

    int deleteOrder(Long id);

    int payOrder(Long id,Long userid);

    List<OrderVO> listOrders(Long id);

    OrderEntity detail(Long id);

    void refundAndreturnRoom(OrderEntity orderEntity, UserEntity userEntity);

}
