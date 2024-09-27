package com.example.Hotel_Management_Service.dto;

import com.example.Hotel_Management_Service.entity.Room;
import com.example.Hotel_Management_Service.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int noOfAdults;

    private int noOfChildren;

    private int totalNoOfGuest;

    private User user;

    private Room room;

}
