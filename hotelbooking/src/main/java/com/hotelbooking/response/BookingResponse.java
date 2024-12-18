package com.hotelbooking.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// Lombok annotation to generate a constructor with all arguments
@AllArgsConstructor
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class  BookingResponse {
    
    // Unique identifier for the booking
    private Long id;

    // Date of check-in for the booking
    private LocalDate checkInDate;

     // Date of check-out for the booking
    private LocalDate checkOutDate;

    // Full name of the guest
    private String guestName;

    // Email address of the guest
    private String guestEmail;

    // Number of adults included in the booking
    private int numOfAdult;


    // Number of children included in the booking
    private int numOfChildren;

    // Total number of guests, calculated as adults + children
     private int totalNumOfGuests;

    // Unique booking confirmation code generated upon booking
     private String bookingConfirmationCode;

     // Associated room details for the booking
    private RoomResponse rooom;
    

     /**
     * Constructor for BookingResponse with essential booking details
     * 
     * @param id                     Unique booking ID
     * @param checkInDate            Date of check-in
     * @param checkOutDate           Date of check-out
     * @param bookingConfirmationCode Confirmation code for the booking
     */
     public BookingResponse(Long id, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode){
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
     }
}
 