package com.example.Hotel_Management_Service.dto;

import com.example.Hotel_Management_Service.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {

    private String roomType;

    private BigDecimal roomPrice;

    private String description;

    private String photoUrl;

    private List<Booking> bookingList;
}
