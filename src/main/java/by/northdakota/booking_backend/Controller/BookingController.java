package by.northdakota.booking_backend.Controller;


import by.northdakota.booking_backend.Dto.BookingDto;
import by.northdakota.booking_backend.Dto.BookingRequest;
import by.northdakota.booking_backend.Service.Interface.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking", description = "Операции с бронированиями")
public class BookingController {

    private final BookingService bookingService;


    @Operation(summary = "Получить все бронирования")
    @ApiResponse(
            responseCode = "200",
            description = "Список всех бронирований",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookingDto.class)))
    )
    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Operation(summary = "Получить бронирование по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Бронирование найдено", content = @Content(schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "418", description = "Пустое бронирование")
    })
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(
            @Parameter(description = "ID бронирования") @PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @Operation(summary = "Создать новое бронирование")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Бронирование успешно создано", content = @Content(schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "409", description = "Комнаты не найдены или уже забронированы"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PostMapping
    public ResponseEntity<?> addBooking(
            @RequestBody @Parameter(description = "Данные для нового бронирования") BookingRequest booking) {
        return bookingService.addBooking(booking);
    }

    @Operation(summary = "Обновить существующее бронирование")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Бронирование обновлено", content = @Content(schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено")
    })
    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(
            @Parameter(description = "ID бронирования") @PathVariable Long bookingId,
            @RequestBody @Parameter(description = "Обновлённые данные бронирования") BookingDto bookingDto) {
        return bookingService.updateBooking(bookingId, bookingDto);
    }

    @Operation(summary = "Удалить бронирование")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Бронирование удалено"),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(
            @Parameter(description = "ID бронирования") @PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    @Operation(summary = "Изменить статус бронирования")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Статус успешно обновлён", content = @Content(schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено")
    })
    @PutMapping("/{bookingId}/{status}")
    public ResponseEntity<?> changeStatus(
            @Parameter(description = "ID бронирования") @PathVariable Long bookingId,
            @Parameter(description = "Новый статус (например, CONFIRMED, CANCELLED)") @PathVariable String status) {
        return bookingService.changeStatus(bookingId, status);
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<?> getUserBookings(@PathVariable Long userId){
        return bookingService.getUserBookings(userId);
    }

}
