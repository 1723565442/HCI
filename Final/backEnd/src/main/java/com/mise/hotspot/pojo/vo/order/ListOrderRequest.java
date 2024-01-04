package com.mise.hotspot.pojo.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "列出订单")
public class ListOrderRequest {
    @Schema(description = "用户ID", required = true)
    @NotNull
    private String id;
}
