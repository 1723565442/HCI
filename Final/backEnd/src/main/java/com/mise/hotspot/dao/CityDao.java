package com.mise.hotspot.dao;

import com.mise.hotspot.pojo.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<CityEntity, Long> {
    CityEntity findByValue(String value);
}
