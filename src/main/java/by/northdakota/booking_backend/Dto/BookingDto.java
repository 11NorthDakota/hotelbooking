package by.northdakota.booking_backend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @Schema(description = "ID бронирования", example = "1001")
    private Long id;

    @Schema(description = "ID пользователя, который создал бронирование", example = "42")
    private Long userId;

    @Schema(description = "ID комнат", example = "[1, 2, 3]")
    private List<Long> roomIds;

    @Schema(description = "Дата и время заезда", example = "2025-05-01T14:00:00")
    private LocalDateTime checkInDate;

    @Schema(description = "Дата и время выезда", example = "2025-05-10T12:00:00")
    private LocalDateTime checkOutDate;

    @Schema(description = "Статус бронирования", example = "NEW")
    private String status;

    @Schema(description = "Количество родителей", example = "1")
    private int numOfAdults;
    @Schema(description = "Количество детей", example = "0")
    private int numOfChildren;
}
