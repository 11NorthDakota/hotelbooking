package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("""
    SELECT b FROM Booking b
    JOIN b.bookingRooms br
    WHERE br.room.id IN :roomIds
      AND (
        (:checkIn BETWEEN b.checkInDate AND b.checkOutDate)
        OR (:checkOut BETWEEN b.checkInDate AND b.checkOutDate)
        OR (b.checkInDate BETWEEN :checkIn AND :checkOut)
      )
""")
    List<Booking> findConflictingBookings(@Param("roomIds") List<Long> roomIds,
                                          @Param("checkIn") LocalDateTime checkIn,
                                          @Param("checkOut") LocalDateTime checkOut);


}
