package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Entity.Booking;
import by.northdakota.booking_backend.RequestEntity.BookingRequest;
import org.springframework.http.ResponseEntity;


public interface BookingService {
    ResponseEntity<?> getAllBookings();
    ResponseEntity<?> getBookingById(Long id);
    ResponseEntity<?> addBooking(BookingRequest booking);
    ResponseEntity<?> deleteBooking(Long id);
    ResponseEntity<?> updateBooking(Long id,Booking booking);
    ResponseEntity<?> changeStatus(Long id,String status);
}
