package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.BookingDto;
import by.northdakota.booking_backend.Entity.*;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.BookingRepository;
import by.northdakota.booking_backend.Repository.RoomRepository;
import by.northdakota.booking_backend.Repository.UserRepository;
import by.northdakota.booking_backend.Dto.BookingRequest;
import by.northdakota.booking_backend.Service.Interface.BookingService;
import by.northdakota.booking_backend.Util.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDto> bookingDtos = bookings.stream()
                .map(mapper::bookingToDto).toList();
        return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBookingById(Long id) {
        if (!bookingRepository.existsById(id)) {
            return new ResponseEntity<>(new NotFoundException("user not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            return new ResponseEntity<>(new NotFoundException("empty data"), HttpStatus.I_AM_A_TEAPOT);
        }
        BookingDto bookingDto = mapper.bookingToDto(booking.get());
        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBooking(BookingRequest bookingRequest) {
        var userOpt = userRepository.findById(bookingRequest.getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException("User not found"));
        }

        List<Long> roomsId = bookingRequest.getRoomsId();
        List<Long> notFoundRooms = checkRoomForExistence(roomsId);
        if (!notFoundRooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(notFoundRooms);
        }

        if (!checkRoomIsAvailable(bookingRequest)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AlreadyExistsException("Some rooms are not available"));
        }

        Booking booking = new Booking(
                null,
                userOpt.get(),
                new ArrayList<>(),
                bookingRequest.getGuestInfo(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate(),
                BookingStatus.NEW
        );

        booking = bookingRepository.save(booking);

        Booking finalBooking = booking;
        List<BookingRoom> bookingRooms = roomsId.stream()
                .map(roomId -> new BookingRoom(null, finalBooking, roomRepository.findById(roomId).orElseThrow()))
                .collect(Collectors.toList());

        booking.setBookingRooms(bookingRooms);

        booking = bookingRepository.save(booking);

        BookingDto bookingDto = mapper.bookingToDto(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @Override
    public ResponseEntity<?> deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            return new ResponseEntity<>(new NotFoundException("booking not found"), HttpStatus.NOT_FOUND);
        }
        bookingRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBooking(Long id, Booking booking) {
        if(!bookingRepository.existsById(id)) {
            return new ResponseEntity<>(new NotFoundException("booking not found"), HttpStatus.NOT_FOUND);
        }
        booking.setId(id);
        bookingRepository.save(booking);
        Booking savedBooking = bookingRepository.save(booking);
        BookingDto updatedBooking = mapper.bookingToDto(savedBooking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeStatus(Long id, String status) {
        if(!bookingRepository.existsById(id)) {
            return new ResponseEntity<>(new NotFoundException("booking not found"), HttpStatus.NOT_FOUND);
        }
        Booking booking = bookingRepository.findById(id).get();
        booking.setStatus(BookingStatus.valueOf(status));
        Booking savedBooking = bookingRepository.save(booking);
        BookingDto updatedBooking = mapper.bookingToDto(savedBooking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }


    private boolean checkRoomIsAvailable(BookingRequest newBooking) {
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(
                newBooking.getRoomsId(),
                newBooking.getCheckInDate(),
                newBooking.getCheckOutDate()
        );
        return conflictingBookings.isEmpty();
    }


    private List<Long> checkRoomForExistence(List<Long> roomsId) {
        Set<Long> existingRoomIds = new HashSet<>(roomRepository.findExistingIds(roomsId));

        return roomsId.stream()
                .filter(id -> !existingRoomIds.contains(id))
                .toList();
    }


}
