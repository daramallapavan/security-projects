package com.example.Hotel_Management_Service.serviceImpl;

import com.example.Hotel_Management_Service.dto.RoomDto;
import com.example.Hotel_Management_Service.entity.Room;
import com.example.Hotel_Management_Service.exception.UserNotFoundException;
import com.example.Hotel_Management_Service.repository.RoomRepository;
import com.example.Hotel_Management_Service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(RoomDto roomDto) {


            Room room=  Room.builder()
                    .roomType( roomDto.getRoomType() )
                    .roomPrice( roomDto.getRoomPrice() )
                    .roomPhotoUrl( roomDto.getPhotoUrl() )
                    .description( roomDto.getDescription() )
                    .build();
            Room savedRoom=roomRepository.save( room );



        return savedRoom;

    }

    @Override
    public Room updateRoom(RoomDto roomDto, Long id) {

     Room room= roomRepository.findById( id ).orElseThrow(()->new UserNotFoundException( " room id not exist" ) );
     room.setRoomType( roomDto.getRoomType() );
     room.setRoomPhotoUrl( roomDto.getPhotoUrl() );
     room.setDescription( roomDto.getDescription() );
     room.setRoomPrice( roomDto.getRoomPrice() );
     room.setId(id );
     Room savedRoom=roomRepository.save( room );
        return savedRoom;
    }
}
