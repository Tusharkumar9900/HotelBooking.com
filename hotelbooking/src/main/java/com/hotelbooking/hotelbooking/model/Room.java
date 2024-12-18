package com.hotelbooking.hotelbooking.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


// Specifies that this class is a JPA entity mapped to a database table
@Entity

// Lombok annotations to generate getter, setter, and all-args constructor
@Getter
@Setter
@AllArgsConstructor
public class Room {


    // Primary key for the Room entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    // Type of the room, e.g., Single, Double, Suite
    private String roomType;


    // Price of the room per night
    private BigDecimal roomPrice;

    private boolean isBooked = false;


    // One-to-many relationship with BookedRoom entity
    // FetchType.LAZY - Loads bookings only when accessed
    // CascadeType.ALL - Propagates all changes to associated bookings
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;

    // Default constructor initializing bookings list
    public Room(){
        this.bookings = new ArrayList<>();
        
    }

     /**
     * Adds a booking to the room
     * 
     * @param booking - New booking to be added
     */
    public void addBooking(BookedRoom booking){
        if(bookings == null){
            bookings = new ArrayList<>();
        }
        // Add the booking to the list and associate it with this room
        bookings.add(booking);
        booking.setRoom(this);

         // Mark room as booked
        isBooked = true;

        // Generate a unique booking confirmation code
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);
    }
}
