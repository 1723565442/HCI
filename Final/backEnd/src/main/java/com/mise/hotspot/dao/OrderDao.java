package com.mise.hotspot.dao;

import com.mise.hotspot.pojo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);

}
