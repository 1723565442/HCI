package com.mise.hotspot.service.implement;

import com.mise.hotspot.dao.CityDao;
import com.mise.hotspot.dao.HotelDao;
import com.mise.hotspot.pojo.entity.CityEntity;
import com.mise.hotspot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;
    @Override
    public List<CityEntity> getCity() {
        return cityDao.findAll();
    }
}
