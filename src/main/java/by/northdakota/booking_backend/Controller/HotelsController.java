package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Review;
import by.northdakota.booking_backend.Service.Interface.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelsController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable Long hotelId,
                                         @RequestBody Hotel hotel) {
        return hotelService.updateHotel(hotelId,hotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {
        return hotelService.deleteHotel(hotelId);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable Long hotelId) {
        return hotelService.getHotelById(hotelId);
    }


    @GetMapping("/{hotelId}/available-rooms")
    public ResponseEntity<?> getAvailableRooms(@PathVariable Long hotelId,
                                               @RequestParam LocalDateTime checkInDate,
                                               @RequestParam LocalDateTime checkOutDate) {
        return hotelService.getAvailableRoomsInHotelByDate(hotelId,checkInDate,checkOutDate);
    }

    @GetMapping("/{hotelId}/reviews")
    public ResponseEntity<?> getHotelReviews(@PathVariable Long hotelId){
        return hotelService.getAllHotelReviews(hotelId);
    }

    @PutMapping("/{hotelId}/add-reviews")
    public ResponseEntity<?> addReviewToHotel(@PathVariable Long hotelId,
                                              @RequestBody Review review){
        return hotelService.addHotelReview(hotelId,review);
    }
}
