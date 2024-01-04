package com.mise.hotspot.pojo.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "登录请求")
public class LoginRequest {

    @Schema(description = "用户名", required = true)
    @NotNull
    private String username;

    @Schema(description = "密码", required = true)
    @NotNull
    private String password;

}
