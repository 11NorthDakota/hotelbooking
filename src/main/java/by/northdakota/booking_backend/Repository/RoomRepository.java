package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
