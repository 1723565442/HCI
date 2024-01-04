package com.mise.hotspot.pojo.vo.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "获得推荐的酒店")
public class GetRecommendRequest {
    @Schema(description = "城市名", required = true)
    @NotNull
    private String city;
}
