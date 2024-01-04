package com.mise.hotspot.pojo.vo.hotel;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "根据需求获得需要的酒店")
public class GetSearchRequest {
    @Schema(description = "城市", required = true)
    @NotNull
    private String city;

    @Schema(description = "入住日期", required = true)
    @NotNull
    private String checkIn;

    @Schema(description = "离店日期", required = true)
    @NotNull
    private String checkOut;
}
