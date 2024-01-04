package com.mise.hotspot.pojo.vo.room;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "获得房间的细节")
public class GetRoomDetailRequest {
    @Schema(description = "房间id", required = true)
    @NotNull
    private Long id;
}
