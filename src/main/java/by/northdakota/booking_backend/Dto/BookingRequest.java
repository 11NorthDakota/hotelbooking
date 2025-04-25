package by.northdakota.booking_backend.Dto;

import by.northdakota.booking_backend.Entity.GuestInfo;
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

    private Long userId;
    private List<Long> roomsId;
    private GuestInfo guestInfo;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

}
