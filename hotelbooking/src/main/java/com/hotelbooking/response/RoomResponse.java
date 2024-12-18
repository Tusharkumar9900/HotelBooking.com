package com.hotelbooking.response;

import java.math.BigDecimal;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to auto-generate getters, setters, toString, equals, and hashCode
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
public class RoomResponse {
    
     // Unique identifier for the room
    private Long id;


    // Type of the room (e.g., Single, Double, Suite)
    private String roomType;

     // Price of the room 
    private BigDecimal roomPrice;

    // Booking status of the room (true if booked, false otherwise)
    private boolean isBooked;
    
    // Base64-encoded string representing the room's photo
    private String photo;


    // List of bookings associated with this room
    private List<BookingResponse> bookings;
    

     /**
     * Constructor for RoomResponse with basic room details
     * 
     * @param id        Unique room ID
     * @param roomType  Type of the room
     * @param roomPrice Price per night for the room
     */
    public RoomResponse(Long id, String roomType, BigDecimal roomPrice){
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }


     /**
     * Full constructor for RoomResponse with additional booking details and photo
     * 
     * @param id          Unique room ID
     * @param roomType    Type of the room
     * @param roomPrice   Price per night for the room
     * @param isBooked    Booking status of the room
     * @param photoBytes  Raw byte array of the room's photo
     * @param bookings    List of booking details associated with the room
     */
    public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, byte[] photoBytes, List<BookingResponse> bookings){
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;

        // Converts the byte array into a Base64-encoded string for easier data transfer
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
        // Sets the list of bookings for the room
        this.bookings = bookings;
    }
}
