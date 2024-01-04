package com.mise.hotspot.method;

import com.mise.hotspot.pojo.entity.OrderEntity;
import com.mise.hotspot.pojo.entity.UserEntity;

public class refundMethod {
    public static void refund(OrderEntity orderEntity, UserEntity userEntity) {
        long  newBalance = userEntity.getBalance() + orderEntity.getPrice();
        userEntity.setBalance(newBalance);
        orderEntity.setStatus("已退款");
    }
}
