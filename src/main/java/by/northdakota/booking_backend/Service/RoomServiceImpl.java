package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.RoomDto;
import by.northdakota.booking_backend.Entity.Room;
import by.northdakota.booking_backend.Repository.RoomRepository;
import by.northdakota.booking_backend.Util.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl {

    private final RoomRepository roomRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public ResponseEntity<?> findRoomsByBookings(List<Long> bookingIds) {
        List<Room> rooms = roomRepository.findAllById(bookingIds);
        List<RoomDto> roomsDto = rooms.stream().map(objectMapper::roomToDto).toList();
        return new ResponseEntity<>(roomsDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> getHotelIdByRoomId(Long roomId){
        Optional<Room> room = roomRepository.findById(roomId);
        Long hotelId = room.get().getHotel().getId();
        return new ResponseEntity<>(hotelId,HttpStatus.OK);
    }

}
