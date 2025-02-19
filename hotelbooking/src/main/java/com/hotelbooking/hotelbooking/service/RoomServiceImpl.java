package com.hotelbooking.hotelbooking.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

// import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotelbooking.hotelbooking.exception.ResourceNotFoundException;
import com.hotelbooking.hotelbooking.model.Room;
import com.hotelbooking.hotelbooking.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService{
    

    private final RoomRepository roomRepository;


    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException{
        
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


    @Override
    public List<String> getAllRoomTypes() {
       return roomRepository.findDistinctRoomTypes();
    }


    @Override
    public List<Room> getAllRooms() {
    return roomRepository.findAll();  
    }


    @Override
    public byte[] getRoomPhotoByRoomId(Long roomid) {
      Optional<Room> theRoom = roomRepository.findById(roomid);
      if(theRoom.isEmpty()) {
          throw new ResourceNotFoundException("Room not found with id: " + roomid);
      }
      Blob photoBlob = theRoom.get().getPhoto();
      if (photoBlob != null) {
        try {
            return photoBlob.getBytes(1, (int) photoBlob.length());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving room photo", e);
        }
    }
      return null;
    }
}
