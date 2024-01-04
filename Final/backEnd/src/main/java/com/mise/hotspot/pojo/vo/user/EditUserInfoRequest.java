package com.mise.hotspot.pojo.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "编辑用户信息请求")
public class EditUserInfoRequest {

    private String id;

    @Schema(description = "用户名", required = true)
    @NotNull(message = "不能为空")
    private String username;


    @Schema(description = "手机号", required = true)
    // @NotNull(message = "不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Schema(description = "密码")
    @Size(min = 8, max = 16, message = "密码长度必须在 8-16 之间")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$", message = "密码只能包含数字、字母和符号")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+", message = "密码必须同时包含大小写字母和数字")
    private String password;




}
