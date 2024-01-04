package com.mise.hotspot.dao;

import com.mise.hotspot.pojo.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelDao extends JpaRepository<HotelEntity, Long> {
    HotelEntity findByName(String name);

    List<HotelEntity> findAllByCity(String city);
}
