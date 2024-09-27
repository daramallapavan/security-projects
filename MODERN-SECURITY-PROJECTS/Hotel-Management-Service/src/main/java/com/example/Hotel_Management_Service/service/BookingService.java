package com.example.Hotel_Management_Service.service;

import com.example.Hotel_Management_Service.dto.BookingDto;
import com.example.Hotel_Management_Service.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Long roomId, String email, BookingDto bookingRequest);

    List<Booking> getAllBookings();


    List<Booking> getAllUserBookings(String email);

    Booking getBookingDetails(String confirmationCode);
}
