package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Entity.Booking;
import by.northdakota.booking_backend.Entity.BookingStatus;
import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Room;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.BookingRepository;
import by.northdakota.booking_backend.Repository.HotelRepository;
import by.northdakota.booking_backend.Repository.RoomRepository;
import by.northdakota.booking_backend.Repository.UserRepository;
import by.northdakota.booking_backend.Service.Interface.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<Booking>> getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBooking(Booking booking) {
        if(bookingRepository.existsById(booking.getId())) {
            return new ResponseEntity<>(new AlreadyExistsException("booking already exists"), HttpStatus.CONFLICT);
        }
        if(!userRepository.existsById(booking.getUser().getId())){
            return new ResponseEntity<>(new NotFoundException("user not found"),HttpStatus.NOT_FOUND);
        }
        boolean hotelExists = booking.getRooms().stream()
                .map(Room::getHotel)
                .map(Hotel::getId)
                .distinct()
                .allMatch(hotelRepository::existsById);

        if (!hotelExists) {
            return new ResponseEntity<>(new NotFoundException("hotel not found"), HttpStatus.NOT_FOUND);
        }

        boolean roomExists = booking.getRooms().stream()
                .allMatch(room -> roomRepository.existsById(room.getId()));

        if(!roomExists){
            return new ResponseEntity<>(new NotFoundException("room not found"),HttpStatus.NOT_FOUND);
        }

        if(!checkRoomIsAvailable(booking)){
            return new ResponseEntity<>(new AlreadyExistsException("this rooms is not available"),HttpStatus.CONFLICT);
        }

        bookingRepository.save(booking);

        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBooking(Long id) {
        if(!bookingRepository.existsById(id)){
            return new ResponseEntity<>(new NotFoundException("booking not found"),HttpStatus.NOT_FOUND);
        }
        bookingRepository.deleteById(id);
        return new ResponseEntity<>(bookingRepository.findById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBooking(Long id, Booking booking) {
        if(!bookingRepository.existsById(id)){
            return new ResponseEntity<>(new NotFoundException("booking not found"),HttpStatus.NOT_FOUND);
        }
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeStatus(Long id, String status) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("booking not found"),HttpStatus.NOT_FOUND);
        }
        try{
            BookingStatus newStatus = BookingStatus.valueOf(status);
            Booking updatedBooking = booking.get();
            updatedBooking.setStatus(newStatus);
            bookingRepository.save(updatedBooking);
            return new ResponseEntity<>(updatedBooking,HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(new NotFoundException("invalid status"),HttpStatus.BAD_REQUEST);
        }
    }


    private boolean checkRoomIsAvailable(Booking newBooking) {
        List<Booking> existingBookings = bookingRepository.findAll();

        return existingBookings.stream()
                .filter(existing -> existing.getRooms().stream()
                        .anyMatch(room -> newBooking.getRooms().stream()
                                .anyMatch(newRoom -> newRoom.getId().equals(room.getId()))))
                .anyMatch(existing -> newBooking.getCheckInDate().isBefore(existing.getCheckOutDate()) &&
                        newBooking.getCheckOutDate().isAfter(existing.getCheckInDate()));
    }

}
