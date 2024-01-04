package com.mise.hotspot.service.implement;

import com.mise.hotspot.dao.RoomDao;
import com.mise.hotspot.pojo.entity.HotelEntity;
import com.mise.hotspot.pojo.entity.RoomEntity;
import com.mise.hotspot.pojo.vo.hotel.HotelVO;
import com.mise.hotspot.service.HotelService;
import com.mise.hotspot.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;
    @Override
    public RoomEntity getRoomDetail(Long Id) {
        return roomDao.findById(Id).orElse(null);
    }

}
