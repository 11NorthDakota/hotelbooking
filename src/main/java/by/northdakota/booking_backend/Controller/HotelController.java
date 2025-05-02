package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Dto.HotelDto;
import by.northdakota.booking_backend.Dto.ReviewDto;
import by.northdakota.booking_backend.Service.Interface.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
@Tag(name = "Hotel Controller", description = "Управление отелями, отзывами и номерами")
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "Добавить отель")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отель добавлен"),
            @ApiResponse(responseCode = "409", description = "Отель уже существует")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addHotel(@RequestBody HotelDto hotel) {
        return hotelService.addHotel(hotel);
    }

    @Operation(summary = "Получить все отели")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отелей получен")
    })
    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Обновить отель по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отель обновлён"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId,
            @RequestBody HotelDto hotelDto
    ) {
        return hotelService.updateHotel(hotelId, hotelDto);
    }

    @Operation(summary = "Удалить отель по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отель удалён"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotel(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId
    ) {
        return hotelService.deleteHotel(hotelId);
    }

    @Operation(summary = "Получить отель по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отель найден"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotel(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId
    ) {
        return hotelService.getHotelById(hotelId);
    }

    @Operation(summary = "Получить доступные номера по датам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список доступных номеров"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @GetMapping("/{hotelId}/available-rooms")
    public ResponseEntity<?> getAvailableRooms(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId,
            @Parameter(description = "Дата заезда (yyyy-MM-ddTHH:mm:ss)") @RequestParam LocalDateTime checkInDate,
            @Parameter(description = "Дата выезда (yyyy-MM-ddTHH:mm:ss)") @RequestParam LocalDateTime checkOutDate
    ) {
        return hotelService.getAvailableRoomsInHotelByDate(hotelId, checkInDate, checkOutDate);
    }

    @Operation(summary = "Получить отзывы об отеле")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзывы получены"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @GetMapping("/{hotelId}/reviews")
    public ResponseEntity<?> getHotelReviews(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId
    ) {
        return hotelService.getAllHotelReviews(hotelId);
    }

    @Operation(summary = "Добавить отзыв к отелю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв добавлен"),
            @ApiResponse(responseCode = "404", description = "Отель не найден")
    })
    @PutMapping("/{hotelId}/add-reviews")
    public ResponseEntity<?> addReviewToHotel(
            @Parameter(description = "ID отеля") @PathVariable Long hotelId,
            @RequestBody ReviewDto review
    ) {
        return hotelService.addHotelReview(hotelId, review);
    }
}