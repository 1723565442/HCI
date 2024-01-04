package com.mise.hotspot.method;

import com.mise.hotspot.pojo.entity.OrderEntity;
import com.mise.hotspot.pojo.entity.UserEntity;

public class payMethod {
    public static void pay(OrderEntity orderEntity, UserEntity userEntity) {
        if(orderEntity.getPrice()>userEntity.getBalance()){
            //余额不足，交易失败
        }else{
            long  newBalance = userEntity.getBalance() - orderEntity.getPrice();
            userEntity.setBalance(newBalance);
            orderEntity.setStatus("已支付");
        }

    }
}
