package com.example.Hotel_Management_Service.service;

import com.example.Hotel_Management_Service.dto.RoomDto;
import com.example.Hotel_Management_Service.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    

    List<Room> findAllRooms();

    Room addRoom(RoomDto roomDto);

    Room updateRoom(RoomDto roomDto, Long id);
}
