package by.northdakota.booking_backend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для отзыва к отелю")
public class ReviewDto {

    @Schema(description = "ID отзыва", example = "100")
    private Long id;

    @Schema(description = "Текст отзыва", example = "Отличный отель, чисто и уютно!")
    private String message;

    @Schema(description = "ID пользователя, оставившего отзыв", example = "42")
    private Long user_id;

    @Schema(description = "ID отеля, к которому оставлен отзыв", example = "3")
    private Long hotel_id;
}
