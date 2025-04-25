package by.northdakota.booking_backend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "DTO отеля с основными данными и списком номеров")
public class HotelDto {

    @Schema(description = "ID отеля", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "Гранд Отель")
    private String hotelName;

    @Schema(description = "Расположение отеля", example = "Санкт-Петербург, Невский проспект, 10")
    private String location;

    @Schema(description = "Описание отеля", example = "Современный отель с видом на набережную.")
    private String description;

    @Schema(description = "Список комнат в отеле")
    private List<RoomDto> rooms;
}