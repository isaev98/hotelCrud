package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.entity.Hotel;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/list")
    public List<Hotel> hotels(){

        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Hotel> hotel(@PathVariable int id){
        return hotelRepository.findById(id);
    }
    @PostMapping("add")
    public String addHotel(@RequestBody Hotel hotel){
         hotelRepository.save(hotel);
         return "Saved";
    }
    @PutMapping("/edit/{id}")
    public String editHotel(@RequestBody Hotel hotel,@PathVariable int id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(!byId.isPresent()) {return "Not Found";}
        Hotel hotel1 = byId.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Saved";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@PathVariable int id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(!byId.isPresent()) {return "Hotel not found";}
        hotelRepository.deleteById(id);
        return "Deleted";
    }
}
