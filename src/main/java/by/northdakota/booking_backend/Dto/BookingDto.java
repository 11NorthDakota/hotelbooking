package by.northdakota.booking_backend.Dto;

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
    private Long id;
    private Long userId;
    private List<Long> roomIds;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String status;
}