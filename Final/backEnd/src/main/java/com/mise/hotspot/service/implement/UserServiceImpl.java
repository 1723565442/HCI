package com.mise.hotspot.service.implement;

import com.mise.hotspot.pojo.entity.UserEntity;
import com.mise.hotspot.pojo.vo.user.UserVO;
import com.mise.hotspot.service.UserService;
import com.mise.hotspot.exception.BizError;
import com.mise.hotspot.incantation.exception.BizException;
import com.mise.hotspot.dao.UserDao;
import cn.dev33.satoken.secure.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public void register(String username, String password, String phone) {
        UserEntity newUser = UserEntity.builder().username(username).phone(phone).password(BCrypt.hashpw(password)).build();
        userDao.save(newUser);
    }

    @Override
    public UserEntity login(String username, String password) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity editInfo(String id,String username, String phone, String password) {
        UserEntity user = userDao.findById(Long.parseLong(id));
        if (userDao.findByUsername(username) != null && !Objects.equals(user.getUsername(), username)) {
            System.out.println("用户存在");
            return null;
        }
        if (password != null) {
            userDao.save(user.setPhone(phone).setUsername(username).setPassword(BCrypt.hashpw(password)));
        }
        else
            userDao.save(user.setPhone(phone).setUsername(username));
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity getUser(String username) {
        return userDao.findByUsername(username);
    }

}
