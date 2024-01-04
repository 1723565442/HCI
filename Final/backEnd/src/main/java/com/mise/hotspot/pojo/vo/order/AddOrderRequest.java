package com.mise.hotspot.pojo.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "添加订单")
public class AddOrderRequest {

    @Schema(description = "订单号", required = true)
    @NotNull
    private Long id;

    @Schema(description = "酒店名字")
    @NotNull
    private String hotelName;

    @Schema(description = "房间号", required = true)
    @NotNull
    private String roomNumber;

    @Schema(description = "入住人姓名", required = true)
    @NotNull
    private String guestName;

    @Schema(description = "订单价格", required = true)
    @NotNull
    private int price;

    @Schema(description = "入住人数", required = true)
    @NotNull
    private Integer guestsCount;

    @Schema(description = "入住日期", required = true)
    @NotNull
    private String checkInDate;

    @Schema(description = "离店日期", required = true)
    @NotNull
    private String checkOutDate;

    @Schema(description = "订单状态", required = true)
    @NotNull
    private String status;

    @Schema(description = "电话号码", required = true)
    @NotNull
    private String phoneNumber;

    @Schema(description = "房间类型", required = true)
    @NotNull
    private String  roomType;

    @Schema(description = "订单备注")
    private String  OrderNote;

    @Schema(description = "用户id", required = true)
    @NotNull
    private Long  userId;



}
