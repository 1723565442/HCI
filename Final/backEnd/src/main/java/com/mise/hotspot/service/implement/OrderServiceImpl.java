package com.mise.hotspot.service.implement;


import com.mise.hotspot.dao.HotelDao;
import com.mise.hotspot.dao.RoomDao;
import com.mise.hotspot.dao.UserDao;
import com.mise.hotspot.method.payMethod;
import com.mise.hotspot.method.refundMethod;
import com.mise.hotspot.method.returnRoomMethod;
import com.mise.hotspot.dao.OrderDao;
import com.mise.hotspot.pojo.entity.HotelEntity;
import com.mise.hotspot.pojo.entity.OrderEntity;
import com.mise.hotspot.pojo.entity.UserEntity;
import com.mise.hotspot.pojo.vo.order.OrderVO;
import com.mise.hotspot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final HotelDao hotelDao;
    private final RoomDao roomDao;

    @Override
    public OrderEntity addOrder(Long id,String hotelName, String roomNumber, String guestName, Integer price, Integer guestsCount, String checkInDate, String checkOutDate, String phoneNumber,String roomType,  String orderNote, Long userId) {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        String formattedDate = today.format(formatter);

        HotelEntity hotel = hotelDao.findByName(hotelName);
        LocalDate baseDate = LocalDate.of(2024, 1, 4);
        LocalDate CheckInDate = LocalDate.parse(checkInDate.substring(0, 10), DateTimeFormatter.ISO_DATE);
        LocalDate CheckOutDate = LocalDate.parse(checkOutDate.substring(0,10), DateTimeFormatter.ISO_DATE);
        int startID = (int) baseDate.until(CheckInDate, java.time.temporal.ChronoUnit.DAYS);
        int endID = (int) baseDate.until(CheckOutDate, java.time.temporal.ChronoUnit.DAYS);
        int tmp = 0;
        for (int i = 0;i<hotel.getRooms().size();i++){
            if (roomDao.findById(hotel.getRooms().get(i)).get().getName().equals(roomType))
            {
                tmp = i;
                break;
            }
        }
        System.out.println();

        System.out.println("删除前" + hotel.getRemains());

        List<String> re = hotel.getRemains();
        for (int i = startID*hotel.getRooms().size()+tmp;i<endID*hotel.getRooms().size();i+=hotel.getRooms().size()){
            re.set(i, String.valueOf((Long.parseLong(re.get(i)) - 1L)));
        }
        hotel.setRemains(re);
        System.out.println("删除后" + re);
        hotelDao.save(hotel);


        OrderEntity order = OrderEntity.builder()
                .hotelName(hotelName)
                .roomNumber(roomNumber)
                .guestName(guestName)
                .price(price)
                .guestsCount(guestsCount)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .phoneNumber(phoneNumber)
                .roomType(roomType)
                .orderNote(orderNote)
                .status("已支付（待入住）")
                .orderTime(formattedDate)
                .userId(userId)
                .build();
        orderDao.save(order);



        return order;
    }

    @Override
    public int cancelOrder(Long id) {
        OrderEntity order = orderDao.findById(id).get();
        order.setStatus("已取消");
        orderDao.save(order);
        return 0;
    }
    @Override
    public int payOrder(Long id,Long userid) {
        OrderEntity order = orderDao.findById(id).get();
        UserEntity user = userDao.findById((long)userid);
        if(user.getBalance()>=order.getPrice()){
            long tmp = user.getBalance()-order.getPrice();
            user.setBalance(tmp);
        }else {
//            余额不足
        }
        order.setStatus("已支付");
        orderDao.save(order);
        return 0;
    }

    @Override
    public int deleteOrder(Long id) {
        OrderEntity order = orderDao.findById(id).get();
        System.out.println(order);
        if (order.getStatus().equals("已支付（待入住）")){
            HotelEntity hotel = hotelDao.findByName(order.getHotelName());
            LocalDate baseDate = LocalDate.of(2024, 1, 4);
            LocalDate checkInDate = LocalDate.parse(order.getCheckInDate().substring(0, 10), DateTimeFormatter.ISO_DATE);
            LocalDate checkOutDate = LocalDate.parse(order.getCheckOutDate().substring(0,10), DateTimeFormatter.ISO_DATE);
            int startID = (int) baseDate.until(checkInDate, java.time.temporal.ChronoUnit.DAYS);
            int endID = (int) baseDate.until(checkOutDate, java.time.temporal.ChronoUnit.DAYS);
            int tmp = 0;
            System.out.println();
            System.out.println();
            System.out.println("曲廷锌");
            System.out.println(startID);
            for (int i = 0;i<hotel.getRooms().size();i++){
                if (roomDao.findById(hotel.getRooms().get(i)).get().getName().equals(order.getRoomType()))
                {
                    tmp = i;
                    break;
                }
            }
            System.out.println();

            System.out.println("回复前" + hotel.getRemains());

            List<String> re = hotel.getRemains();
            for (int i = startID*hotel.getRooms().size()+tmp;i<endID*hotel.getRooms().size();i+=hotel.getRooms().size()){
                re.set(i, String.valueOf((Long.parseLong(re.get(i)) + 1L)));
            }
            hotel.setRemains(re);
            hotelDao.save(hotel);
            System.out.println("恢复后" + hotelDao.findByName(order.getHotelName()).getRemains());
        }

        orderDao.deleteById(id);
        return 0;
    }

    @Override
    public List<OrderVO> listOrders(Long id) {
        List<OrderEntity> orders = orderDao.findByUserId(id);
        return orders.stream().map(order -> OrderVO.builder()
                .id(order.getId())
                .roomNumber(order.getRoomNumber())
                .guestName(order.getGuestName())
                .roomType(order.getRoomType())
                .price(order.getPrice())
                .guestsCount(order.getGuestsCount())
                .checkInDate(order.getCheckInDate())
                .checkOutDate(order.getCheckOutDate())
                .status(order.getStatus())
                .build()).collect(Collectors.toList());
    }

    @Override
    public OrderEntity detail(Long id) {
        System.out.println("impl ID" + id);
        System.out.println(orderDao.findById(id).get());
        return orderDao.findById(id).get();
    }

    @Override
    public void refundAndreturnRoom(OrderEntity orderEntity,UserEntity userEntity) {
        refundMethod.refund(orderEntity,userEntity);
        returnRoomMethod.returnRoom(orderEntity);
    }
}
