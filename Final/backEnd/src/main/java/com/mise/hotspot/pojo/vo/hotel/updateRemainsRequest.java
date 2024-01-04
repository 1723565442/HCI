package com.mise.hotspot.pojo.vo.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "房间信息")
public class updateRemainsRequest {
    @Schema(description = "酒店名字", required = true)
    @NotNull
    private String name;

    @Schema(description = "房间数组", required = true)
    @NotNull
    private List<String> remains;
}
