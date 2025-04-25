package by.northdakota.booking_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String message;
    private Long user_id;
    private Long hotel_id;

}
