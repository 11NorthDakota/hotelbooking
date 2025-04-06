package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Entity.Room;
import by.northdakota.booking_backend.Exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAllRooms();
    Optional<Room> getRoomById(Long id) throws NotFoundException;
    Long saveRoom(Room room);
    void deleteRoom(Long id) throws NotFoundException;

}
