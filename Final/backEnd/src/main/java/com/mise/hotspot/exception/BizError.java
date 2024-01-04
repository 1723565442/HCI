package com.mise.hotspot.exception;

import com.mise.hotspot.incantation.exception.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BizError implements ErrorType {

    USERNAME_EXISTS(200001, "用户名已存在", 400),
    INVALID_CREDENTIAL(200002, "用户名或密码错误", 400),
    USERNAME_DOES_NOT_EXIST(200003, "用户名不存在", 400),
    PASSWORD_WRONG(200004, "用户名或密码错误", 400),
    CITY_EXISTS(200005, "城市已存在", 400),
    BOOK_DOES_NOT_EXIST(200006, "小说不存在", 400),
    BOOK_EXISTS(200007, "小说已存在", 400),
    FILE_NOT_FOUND(200008, "文件不存在", 400);
    // STATIONNAME_EXISTS(200003, "同名站点已存在", 400),
    // TRAIN_EXISTS(200004, "车次已存在", 400),
    // ROUTE_EXISTS(200005, "路线已存在", 400),
    // STATION_CAN_NOT_DELETE(200006, "已有路线包含该站点", 400),
    // ROUTE_CAN_NOT_DELETE(200007, "已有火车采用此路线", 400),
    // TRAIN_TIMES_SIZE_NOT_SAME(200008, "火车经停站时间表长度不一致", 400),
    // TRAIN_TIMES_NOT_COMPLETED(200009, "火车经停站时间表不完整", 400),
    // TRAIN_TIMES_NOT_VALID(200010, "火车经停站时间表不合法", 400),
    // TRAIN_NAME_NOT_VALID(200011, "火车名不规范 例：K000 或 G000", 400),
    // ROUTE_NAME_NOT_VALID(200012, "路线名不规范 例：南京-重庆", 400),
    // STAION_NAME_NOT_VALID(200013, "站名不规范 例：南京站", 400),
    // ROUTE_NAME_NOT_MATCH(200014, "起止站名不匹配", 400),
    // TRAIN_CAN_NOT_DELETE(200015, "已有订单包含该火车", 400),
    // TRAIN_NAME_NOT_MATCH_TYPE(200016, "火车名与类型不匹配：K为快车；G为高铁", 400),
    // OUT_OF_SEAT(300001, "无可用座位", 400),
    // ILLEAGAL_ORDER_STATUS(400001, "非法的订单状态", 400),
    // ALIPAYBALANCE_NOT_ENOUGH(400002, "支付宝余额不足", 400),
    // WECHATBALANCE_NOT_ENOUGH(400003, "微信余额不足", 400);

    final int code;
    final String message;
    final int httpCode;
    
}
