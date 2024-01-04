package com.mise.hotspot.pojo.vo.hotel;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "获得酒店的细节")
public class GetDetailRequest {
    @Schema(description = "酒店名字", required = true)
    @NotNull
    private String name;
}
