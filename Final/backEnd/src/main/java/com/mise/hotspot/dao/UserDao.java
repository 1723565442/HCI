package com.mise.hotspot.dao;

import com.mise.hotspot.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findById(long id);
}
