package com.mise.hotspot.pojo.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "删除订单")
public class DeleteOrderRequest {
    @Schema(description = "订单号", required = true)
    @NotNull
    private String id;
}
