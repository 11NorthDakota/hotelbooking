package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.HotelDto;
import by.northdakota.booking_backend.Dto.RoomDto;
import by.northdakota.booking_backend.Entity.Booking;
import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Review;
import by.northdakota.booking_backend.Entity.Room;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.BookingRepository;
import by.northdakota.booking_backend.Repository.HotelRepository;
import by.northdakota.booking_backend.Repository.RoomRepository;
import by.northdakota.booking_backend.Service.Interface.HotelService;
import by.northdakota.booking_backend.Util.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final ObjectMapper mapper;
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Override
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<HotelDto> hotels = hotelRepository.findAll().stream()
                .map(mapper::hotelToDto)
                .toList();

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getHotelById(Long HotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(HotelId);
        if(hotel.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("empty data"),HttpStatus.NOT_FOUND);
        }
        HotelDto hotelDto = mapper.hotelToDto(hotel.get());
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addHotel(HotelDto hotelDto) {
        Hotel hotel = mapper.dtoToHotel(hotelDto);

        if (hotelRepository.existsById(hotel.getId())) {
            return new ResponseEntity<>(new AlreadyExistsException("hotel already exists"), HttpStatus.CONFLICT);
        }
        hotel = hotelRepository.save(hotel);
        hotelDto = mapper.hotelToDto(hotel);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateHotel(Long hotelId, Hotel hotel) {
        if (!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        hotelRepository.save(hotel);
        HotelDto hotelDto = mapper.hotelToDto(hotel);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteHotel(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        hotelRepository.deleteById(hotelId);
        return new ResponseEntity<>(hotelId, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAvailableRoomsInHotelByDate(Long hotelId,
                                                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkIn,
                                                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkOut) {
        // 1. Все комнаты отеля
        List<Room> allRooms = roomRepository.findByHotelId(hotelId);

        if (allRooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found or no rooms.");
        }

        // 2. Список всех room.id
        List<Long> roomIds = allRooms.stream().map(Room::getId).toList();

        // 3. Занятые бронирования
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(roomIds, checkIn, checkOut);

        // 4. Занятые комнаты
        Set<Long> bookedRoomIds = conflictingBookings.stream()
                .flatMap(booking -> booking.getBookingRooms().stream())
                .map(bookingRoom -> bookingRoom.getRoom().getId())
                .collect(Collectors.toSet());

        // 5. Свободные комнаты
        List<Room> availableRooms = allRooms.stream()
                .filter(room -> !bookedRoomIds.contains(room.getId()))
                .toList();
        List<RoomDto> roomDtos = availableRooms.stream().map(mapper::roomToDto).toList();
        return ResponseEntity.ok(roomDtos);
    }


    @Override
    public ResponseEntity<?> getAllHotelReviews(Long hotelId) {
        if(!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        List<Hotel> hotels = hotelRepository.findAll();
        List<Review> reviews = hotels.stream()
                .flatMap(hotel->hotel.getReviews().stream())
                .toList();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addHotelReview(Long hotelId, Review review) {
        if(!hotelRepository.existsById(hotelId)) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if(hotelOpt.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.I_AM_A_TEAPOT);
        }
        Hotel hotel =  hotelOpt.get();
        hotel.getReviews().add(review);
        hotelRepository.save(hotel);
        HotelDto hotelDto = mapper.hotelToDto(hotel);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }
}
