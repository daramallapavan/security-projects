package com.example.Hotel_Management_Service.controller;

import com.example.Hotel_Management_Service.dto.RoomDto;
import com.example.Hotel_Management_Service.entity.Room;
import com.example.Hotel_Management_Service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @PostMapping( "/createRoom" )
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto){

       Room room= roomService.addRoom(roomDto);
       return new ResponseEntity<>( room, HttpStatus.CREATED );

    }

    @PutMapping( "/updateRoom" )
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<Room> updateRoom(@RequestParam("roomId") Long id,
                                           @RequestBody RoomDto roomDto){

        Room room= roomService.updateRoom(roomDto,id);
        return new ResponseEntity<>( room, HttpStatus.CREATED );

    }
    @GetMapping("/getAllRooms")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> roomList= roomService.findAllRooms();
        return new ResponseEntity<>( roomList,HttpStatus.OK);
    }

}
