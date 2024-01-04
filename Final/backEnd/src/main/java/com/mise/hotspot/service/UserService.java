package com.mise.hotspot.service;

import com.mise.hotspot.pojo.entity.UserEntity;
import com.mise.hotspot.pojo.vo.user.UserVO;

public interface UserService {

    UserEntity login(String username, String password);

    void register(String username, String password, String phone);

    UserEntity editInfo(String id,String username, String phone,String password);


    UserEntity getUser(String username);
}
