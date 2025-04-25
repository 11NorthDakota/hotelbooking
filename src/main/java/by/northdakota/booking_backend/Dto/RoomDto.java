package by.northdakota.booking_backend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "DTO объекта номера в отеле")
public class RoomDto {

    @Schema(description = "ID номера", example = "1")
    private long id;

    @Schema(description = "Тип номера", example = "Люкс")
    private String roomType;

    @Schema(description = "Цена за ночь", example = "4999.99")
    private BigDecimal price;

    @Schema(description = "URL фотографии номера", example = "https://example.com/photos/room1.jpg")
    private String roomPhotoUrl;

    @Schema(description = "Описание номера", example = "Просторный люкс с видом на парк и джакузи")
    private String roomDescription;
}
