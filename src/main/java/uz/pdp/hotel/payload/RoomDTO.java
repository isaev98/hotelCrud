package uz.pdp.hotel.payload;

import lombok.Data;

@Data
public class RoomDTO {
    private int roomNumber;
    private int floor;
    private int size;
    private int hotelId;
}
