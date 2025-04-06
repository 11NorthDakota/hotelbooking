package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Review;
import by.northdakota.booking_backend.Entity.Room;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.BookingRepository;
import by.northdakota.booking_backend.Repository.HotelRepository;
import by.northdakota.booking_backend.Service.Interface.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<Hotel>> getHotelById(Long HotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(HotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addHotel(Hotel hotel) {
        if (hotelRepository.existsById(hotel.getId())) {
            return new ResponseEntity<>(new AlreadyExistsException("hotel already exists"), HttpStatus.CONFLICT);
        }
        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateHotel(Long hotelId, Hotel hotel) {
        if (!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteHotel(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        hotelRepository.deleteById(hotelId);
        return new ResponseEntity<>(hotelRepository.findById(hotelId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAvailableRoomsInHotelByDate(Long hotelId, LocalDateTime checkIn, LocalDateTime checkOut) {

        Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        if(hotel.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }

        List<Room> bookedRooms = bookingRepository.findAll()
                .stream()
                .filter(booking->booking.getCheckInDate().isBefore(checkOut)
                        && booking.getCheckOutDate().isAfter(checkIn))
                .flatMap(booking -> booking.getRooms().stream())
                .filter(room -> room.getHotel().getId().equals(hotelId))
                .toList();

        List<Room> availableRooms = hotel.get().getRooms().stream()
                .filter(room -> !bookedRooms.contains(room))
                .toList();

        if(availableRooms.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("available room not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllHotelReviews(Long hotelId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<?> addHotelReview(Long hotelId, Review review) {
        //TODO
        return null;
    }
}
