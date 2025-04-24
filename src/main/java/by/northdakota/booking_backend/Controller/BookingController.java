package by.northdakota.booking_backend.Controller;


import by.northdakota.booking_backend.Entity.Booking;
import by.northdakota.booking_backend.RequestEntity.BookingRequest;
import by.northdakota.booking_backend.Service.Interface.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest booking) {
        return bookingService.addBooking(booking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable Long bookingId,
                                                 @RequestBody Booking booking) {
        return bookingService.updateBooking(bookingId, booking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    @PutMapping("/{bookingId}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable Long bookingId,
                                                @PathVariable  String status) {
        return bookingService.changeStatus(bookingId,status);
    }
}
