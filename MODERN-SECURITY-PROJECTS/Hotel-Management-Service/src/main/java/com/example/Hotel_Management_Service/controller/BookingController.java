package com.example.Hotel_Management_Service.controller;

import com.example.Hotel_Management_Service.dto.BookingDto;
import com.example.Hotel_Management_Service.entity.Booking;
import com.example.Hotel_Management_Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Booking> saveBooking(@RequestBody BookingDto bookingRequest,
                                               @RequestParam Long roomId,
                                               @RequestParam String email){
      Booking booking=  bookingService.createBooking(roomId,email,bookingRequest);
      return new ResponseEntity<>( booking, HttpStatus.CREATED );
    }

    @GetMapping("/getAllBookings")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<List<Booking>> listOfBooking(){
        List<Booking> bookingList= bookingService.getAllBookings();
        return new ResponseEntity<>( bookingList,HttpStatus.OK );
    }

    @GetMapping("/getALlUserBookings")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<Booking>> listOfBooking(@RequestParam String email){
        List<Booking> bookingList= bookingService.getAllUserBookings(email);
        return new ResponseEntity<>( bookingList,HttpStatus.OK );
    }

    @GetMapping("/getBookingDetails")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Booking> getBookingDetails(@RequestParam String confirmationCode){
       Booking booking= bookingService.getBookingDetails(confirmationCode);
       return new ResponseEntity<>( booking,HttpStatus.OK );
    }

}
