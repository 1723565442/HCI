package com.mise.hotspot.service.implement;

import com.mise.hotspot.dao.HotelDao;
import com.mise.hotspot.pojo.entity.HotelEntity;
import com.mise.hotspot.pojo.vo.hotel.HotelVO;
import com.mise.hotspot.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {
    private final HotelDao hotelDao;
    @Override
    public HotelEntity getHotelDetail(String name) {
        return hotelDao.findByName(name);
    }

    @Override
    public List<HotelVO> getRecommend(String city) {
        List<HotelVO> res = new ArrayList<>();
        List<HotelEntity> tmp = hotelDao.findAllByCity(city);
        for (int i = 0; i< (Math.min(5, tmp.size())); i++){
            res.add(HotelVO.builder().name(tmp.get(i).getName()).price(tmp.get(i).getPrices().get(0))
                            .view(tmp.get(i).getView())
                    .introduction(tmp.get(i).getIntroduction()).build());
        }
        return res;
    }

    @Override
    public List<HotelVO> listHotels(String city, String checkIn, String checkOut) {
        List<HotelEntity> tmp = hotelDao.findAllByCity(city);
        List<HotelVO> res = new ArrayList<>();

        LocalDate baseDate = LocalDate.of(2024, 1, 3);
        LocalDate checkInDate = LocalDate.parse(checkIn.substring(0, 10), DateTimeFormatter.ISO_DATE);
        LocalDate checkOutDate = LocalDate.parse(checkOut.substring(0,10), DateTimeFormatter.ISO_DATE);
        int startID = (int) baseDate.until(checkInDate, java.time.temporal.ChronoUnit.DAYS);
        int endID = (int) baseDate.until(checkOutDate, java.time.temporal.ChronoUnit.DAYS);
        if (endID>30 || startID<0){
            return null;
        }

        for (HotelEntity h: tmp) {
            boolean flag = true;
            for (int i = startID*h.getRooms().size(); i<endID*h.getRooms().size();i+=h.getRooms().size()){
                boolean flag2 = false;
                for (int j = i;j< i+h.getRooms().size();j++){
                    if (Integer.parseInt(h.getRemains().get(j)) > 0) {
                        flag2 = true;
                        break;
                    }
                }
                if (!flag2){
                    flag = false;
                    break;
                }
            }
            if (flag){
                res.add(HotelVO.builder().introduction(h.getIntroduction())
                                .view(h.getView())
                        .name(h.getName()).price(h.getPrices().get(0)).build());
            }
        }
        return res;
    }

    public List<String> updateRemains(String name, List<String> reamins) {
        HotelEntity hotel = hotelDao.findByName(name);

        if (hotel != null) {
            hotel.setRemains(reamins);
            hotelDao.save(hotel);
            return hotel.getRemains();
        } else {
            // 如果没有找到HotelEntity，可能需要处理错误或返回null
            System.out.println("Hotel with name " + name + " not found.");
            return null;
        }
    }

}
