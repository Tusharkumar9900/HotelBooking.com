package com.hotelbooking.hotelbooking.service;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.hotelbooking.hotelbooking.model.Room;

public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice);
    
}
