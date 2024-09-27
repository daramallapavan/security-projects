package com.example.Hotel_Management_Service.repository;

import com.example.Hotel_Management_Service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findBybookingConfirmationCode(String confirmationCode);
}
