package com.example.Hotel_Management_Service.serviceImpl;

import com.example.Hotel_Management_Service.dto.BookingDto;
import com.example.Hotel_Management_Service.entity.Booking;
import com.example.Hotel_Management_Service.entity.Room;
import com.example.Hotel_Management_Service.entity.User;
import com.example.Hotel_Management_Service.exception.UserNotFoundException;
import com.example.Hotel_Management_Service.repository.BookingRepository;
import com.example.Hotel_Management_Service.repository.RoomRepository;
import com.example.Hotel_Management_Service.repository.UserRepository;
import com.example.Hotel_Management_Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(Long roomId, String email, BookingDto bookingRequest) {

        User user= userRepository.findByEmail( email ).orElseThrow(()->new UserNotFoundException(  "User not exist....") );

     Room room=  roomRepository.findById( roomId ).orElseThrow(()->new UserNotFoundException(  "room  not exist....") );

     if (bookingRequest.getCheckOutDate().isBefore( bookingRequest.getCheckInDate() )){
         throw new UserNotFoundException("Check in date must come after check out date");
     }

        List<Booking> existingBookings = room.getBookingList();

        if (!roomIsAvailable(bookingRequest, existingBookings)) {
            throw new UserNotFoundException("Room not Available for selected date range");
        }


        Booking booking=  Booking.builder()
             .bookingConfirmationCode( generateBookingConfirmationCode() )
             .checkInDate( bookingRequest.getCheckInDate() )
             .checkOutDate( bookingRequest.getCheckOutDate() )
             .noOfAdults( bookingRequest.getNoOfAdults() )
             .noOfChildren( bookingRequest.getNoOfChildren() )
             .totalNoOfGuest( bookingRequest.getNoOfAdults()+ bookingRequest.getNoOfChildren() )
             .room( room )
             .user( user )
             .build();


     Booking savedBooking=bookingRepository.save( booking );


        return savedBooking;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getAllUserBookings(String email) {

        User user= userRepository.findByEmail( email ).orElseThrow(()->new UserNotFoundException(  "User not exist....") );
        List<Booking> bookingList=user.getBookings();

        if (bookingList==null){
            throw new UserNotFoundException( "not yet done booking, empty...." );
        }
        return bookingList;
    }

    @Override
    public Booking getBookingDetails(String confirmationCode) {
     Booking booking=   bookingRepository.findBybookingConfirmationCode(confirmationCode).orElseThrow(()->new UserNotFoundException( "failed due to wrong confirmation code,please try different  confirmation code" ));
        return booking;
    }


    private static  String generateBookingConfirmationCode(){
        Random random=new Random();
       int num= random.nextInt(5);
       return String.valueOf( num );
    }

    private boolean roomIsAvailable(BookingDto bookingRequest, List<Booking> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }
}
