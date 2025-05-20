package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRoomsRepository extends JpaRepository< BookingRoom,Long> {
}
