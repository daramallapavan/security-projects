package com.example.Hotel_Management_Service.dto;

import com.example.Hotel_Management_Service.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String email;
    private String name;
    private String phoneNumber;
    private String password;

    private String role;
}
