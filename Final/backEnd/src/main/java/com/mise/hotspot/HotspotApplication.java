package com.mise.hotspot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mise.hotspot.dao.*;
import com.mise.hotspot.pojo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class HotspotApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotspotApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(CityDao repo, HotelDao hotelDao, RoomDao roomDao, UserDao userDao, OrderDao orderDao) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) {


//				OrderEntity newOrder = OrderEntity.builder()
//						.hotelName("北京天伦王朝酒店 (Sunworld Dynasty Hotel Beijing Wangfujing)")
//						.userId(10L)
//						.roomNumber("108")
//						.guestName("曲廷锌")
//						.price(567)
//						.guestsCount(2)
//						.checkInDate("2024-01-03")
//						.checkOutDate("2024-01-04")
//						.phoneNumber("15940785017")
//						.roomType("双床房")
//						.orderNote("无")
//						.status("已支付")
//						.build();
//
//				// Save the new order to the database
//				orderDao.save(newOrder);
			}

		};
	}
}
