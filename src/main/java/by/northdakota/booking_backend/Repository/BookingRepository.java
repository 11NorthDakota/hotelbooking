package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
