package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Dto.HotelDto;
import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Review;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

public interface HotelService {
    ResponseEntity<?> getAllHotels();
    ResponseEntity<?> getHotelById(Long HotelId);
    ResponseEntity<?> addHotel(HotelDto hotel);
    ResponseEntity<?> updateHotel(Long hotelId,Hotel hotel);
    ResponseEntity<?> deleteHotel(Long hotelId);
    ResponseEntity<?> getAvailableRoomsInHotelByDate(Long hotelId, LocalDateTime checkIn, LocalDateTime checkOut);
    ResponseEntity<?> getAllHotelReviews(Long hotelId);
    ResponseEntity<?> addHotelReview(Long hotelId, Review review);
}
