package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Dto.HotelDto;
import by.northdakota.booking_backend.Dto.ReviewDto;
import by.northdakota.booking_backend.Entity.Hotel;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

public interface HotelService {
    ResponseEntity<?> getAllHotels();
    ResponseEntity<?> getHotelById(Long HotelId);
    ResponseEntity<?> addHotel(HotelDto hotel);
    ResponseEntity<?> updateHotel(Long hotelId,HotelDto hotelDto);
    ResponseEntity<?> deleteHotel(Long hotelId);
    ResponseEntity<?> getAvailableRoomsInHotelByDate(Long hotelId, LocalDateTime checkIn, LocalDateTime checkOut);
    ResponseEntity<?> getAllHotelReviews(Long hotelId);
    ResponseEntity<?> addHotelReview(Long hotelId, ReviewDto review);
}
