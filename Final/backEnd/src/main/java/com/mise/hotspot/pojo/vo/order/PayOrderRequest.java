package com.mise.hotspot.pojo.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "支付订单")
public class PayOrderRequest {

    @Schema(description = "订单号", required = true)
    @NotNull
    private Long id;


    @Schema(description = "订单价格", required = true)
    @NotNull
    private int price;



    @Schema(description = "用户ID", required = true)
    @NotNull
    private Long userId;

//    @Schema(description = "订单状态", required = true)
//    @NotNull
//    private String status;
}