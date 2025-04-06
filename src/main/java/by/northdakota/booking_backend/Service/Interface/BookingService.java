package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Entity.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    ResponseEntity<List<Booking>> getAllBookings();
    ResponseEntity<Optional<Booking>> getBookingById(Long id);
    ResponseEntity<?> addBooking(Booking booking);
    ResponseEntity<?> deleteBooking(Long id);
    ResponseEntity<?> updateBooking(Long id,Booking booking);
    ResponseEntity<?> changeStatus(Long id,String status);
}
