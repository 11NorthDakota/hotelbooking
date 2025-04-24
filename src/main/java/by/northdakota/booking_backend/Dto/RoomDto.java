package by.northdakota.booking_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto {

    private long id;
    private String roomType;
    private BigDecimal price;
    private String roomPhotoUrl;
    private String roomDescription;

}
