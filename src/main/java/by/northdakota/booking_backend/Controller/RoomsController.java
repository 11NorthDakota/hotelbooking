package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomServiceImpl roomService;

    @PostMapping
    public ResponseEntity<?> getRoomsByBookingId(@RequestBody List<Long> bookingIds){
        return roomService.findRoomsByBookings(bookingIds);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getHotelByRoomId(@PathVariable Long roomId){
        return roomService.getHotelIdByRoomId(roomId);
    }

}
