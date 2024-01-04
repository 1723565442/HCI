package com.mise.hotspot.service;

import com.mise.hotspot.pojo.entity.HotelEntity;
import com.mise.hotspot.pojo.vo.hotel.HotelVO;

import java.util.List;

public interface HotelService {

    public HotelEntity getHotelDetail(String name);

    public List<HotelVO> getRecommend(String city);

    public List<HotelVO> listHotels(String city, String checkIn, String checkOut);

    public List<String> updateRemains(String name, List<String> reamins);
}
