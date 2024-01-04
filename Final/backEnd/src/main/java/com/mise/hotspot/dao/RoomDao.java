package com.mise.hotspot.dao;

import com.mise.hotspot.pojo.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDao extends JpaRepository<RoomEntity, Long> {

}
