package by.northdakota.booking_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HotelDto {

    private Long id;
    private String hotelName;
    private String location;
    private String description;
    private List<RoomDto> rooms;

}
