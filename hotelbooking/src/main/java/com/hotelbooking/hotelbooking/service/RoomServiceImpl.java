package com.hotelbooking.hotelbooking.service;

import java.math.BigDecimal;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotelbooking.hotelbooking.model.Room;
import com.hotelbooking.hotelbooking.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService{
    

    private final RoomRepository roomRepository;


    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice){
        

        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }

        return roomRepository.save(room);
    }
}
