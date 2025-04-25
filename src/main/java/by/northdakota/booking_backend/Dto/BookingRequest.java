package by.northdakota.booking_backend.Dto;

import by.northdakota.booking_backend.Entity.GuestInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {

    @Schema(description = "ID пользователя", example = "42", required = true)
    private Long userId;

    @Schema(description = "ID комнат", example = "[1, 2, 3]", required = true)
    private List<Long> roomsId;

    @Schema(description = "Информация о госте", required = true)
    private GuestInfo guestInfo;

    @Schema(description = "Дата и время заезда", example = "2025-05-01T14:00:00", required = true)
    private LocalDateTime checkInDate;

    @Schema(description = "Дата и время выезда", example = "2025-05-10T12:00:00", required = true)
    private LocalDateTime checkOutDate;
}
