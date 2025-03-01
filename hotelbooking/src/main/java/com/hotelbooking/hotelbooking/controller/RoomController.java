package com.hotelbooking.hotelbooking.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hotelbooking.hotelbooking.exception.PhotoRetrievalException;
import com.hotelbooking.hotelbooking.model.BookedRoom;
import com.hotelbooking.hotelbooking.model.Room;
import com.hotelbooking.hotelbooking.service.BookedRoomServiceImpl;
import com.hotelbooking.hotelbooking.service.IRoomService;
import com.hotelbooking.response.BookingResponse;
import com.hotelbooking.response.RoomResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    
    private final IRoomService roomService;

    private final BookedRoomServiceImpl bookingService;

    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom(
        @RequestParam("photo")MultipartFile photo,
        @RequestParam("roomType")String roomType, 
        @RequestParam("roomPrice")BigDecimal roomPrice) throws SQLException, IOException{
            Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
            RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
    return ResponseEntity.ok(response);

        }

        @GetMapping("/room-types")
        public List<String> getRoomTypes(){
            return roomService.getAllRoomTypes();
        }

        @GetMapping("/all-rooms")
        public ResponseEntity<List<RoomResponse>> getAllRooms()throws SQLException{
            List<Room> rooms = roomService.getAllRooms();
            List<RoomResponse> roomResponses = new ArrayList<>();
            for(Room room: rooms){
                byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
                if(photoBytes != null && photoBytes.length > 0){
                    String base64Photo = Base64.encodeBase64String(photoBytes);
                    RoomResponse roomResponse = getRoomResponse(room);
                    roomResponse.setPhoto(base64Photo);
                    roomResponses.add(roomResponse);
            }
        }
        return ResponseEntity.ok(roomResponses);
}

            private RoomResponse getRoomResponse(Room room){
                List<BookedRoom> bookings = getAllBookingsByRoomId(room.getId());
                List<BookingResponse> bookingInfo = bookings
                .stream()
                .map(booking -> new BookingResponse(booking.getBookingId(),
                                                    booking.getCheckInDate(), 
                                                    booking.getCheckOutDate(),
                                                    booking.getBookingConfirmationCode())).toList();


            byte[] photoBytes = null;
            Blob photoBlob = room.getPhoto();
            if(photoBlob != null){
                try{
                        photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                }catch(SQLException e){
                    throw new PhotoRetrievalException("Error retrieving photo for room with id: " + room.getId());

                }
            }
            return new RoomResponse(room.getId(), room.getRoomType(), room.getRoomPrice(), room.isBooked(),  photoBytes, bookingInfo);
            }

            private List<BookedRoom> getAllBookingsByRoomId(Long roomId){
                return bookingService.getAllBookingsByRoomId(roomId);
            }
}
