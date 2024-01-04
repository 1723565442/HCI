package com.mise.hotspot.controller;

// import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.secure.BCrypt;
import com.mise.hotspot.exception.BizError;
import com.mise.hotspot.pojo.entity.UserEntity;
import com.mise.hotspot.service.UserService;
import com.mise.hotspot.pojo.vo.user.*;
import com.mise.hotspot.incantation.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public CommonResponse<?> login(@Valid @RequestBody LoginRequest request) {
        UserEntity user= userService.getUser(request.getUsername());
        if (user == null) {
            System.out.println("用户不存在");
            return CommonResponse.error(BizError.USERNAME_DOES_NOT_EXIST);
        }
        String password =user.getPassword();
        if(!BCrypt.checkpw(request.getPassword(), password)){
            System.out.println("密码错误");
            return CommonResponse.error(BizError.PASSWORD_WRONG);
        }
        return CommonResponse.success(userService.login(request.getUsername(), request.getPassword()));
    }

    @PostMapping("reg")
    public CommonResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        UserEntity user= userService.getUser(request.getUsername());
        if (user!= null) {
            return CommonResponse.error(BizError.USERNAME_EXISTS);
        }
        userService.register(request.getUsername(), request.getPassword(),request.getPhone());
        return CommonResponse.success();
    }

    @PostMapping("edit")
    public CommonResponse<?> editInfo(@Valid @RequestBody EditUserInfoRequest request) {
        System.out.println("曲廷锌" + request);
        UserEntity user = userService.editInfo(request.getId(),request.getUsername(),request.getPhone(),request.getPassword());
        if (user == null)
            return CommonResponse.error(BizError.USERNAME_EXISTS);
        return CommonResponse.success(user);
    }

    @DeleteMapping("out")
    public CommonResponse<?> logout() {
        return CommonResponse.success(200);
    }

}
