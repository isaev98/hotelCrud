package uz.pdp.hotel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.payload.RoomDTO;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/page")
    public Page<Room> roomPage(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,1);
        return roomRepository.findAll(pageable);
    }

    @PostMapping("/add")
    public String addHotel(@RequestBody RoomDTO roomDTO){
        Room room=new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(roomDTO.getSize());
        Optional<Hotel> byId = hotelRepository.findById(roomDTO.getHotelId());
        if(!byId.isPresent()){return "Hotel NOt Found";}
        room.setHotel(byId.get());
        roomRepository.save(room);
        return "Saved";
    }

    @PutMapping("/edit/{id}")
    public String editHotel(@RequestBody RoomDTO roomDTO, @PathVariable int id){
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()){return "Room not found";}
        Room room = byId.get();
       if (roomDTO.getRoomNumber()!=0){
           room.setRoomNumber(roomDTO.getRoomNumber());
       }
        if (roomDTO.getSize()!=0){
            room.setSize(roomDTO.getSize());
        }
       if (roomDTO.getFloor()!=0){
           room.setFloor(roomDTO.getFloor());
       }
        roomRepository.save(room);
        return "Edited";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable int id){
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()){return "Room not found";}
        roomRepository.deleteById(id);
        return "Deleted";

    }
}
