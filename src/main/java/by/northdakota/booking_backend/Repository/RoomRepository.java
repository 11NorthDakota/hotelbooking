package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelId(Long hotelId);

    @Override
    List<Room> findAllById(Iterable<Long> longs);

    @Query("SELECT r.id FROM Room r WHERE r.id IN :roomIds")
    Set<Long> findExistingIds(@Param("roomIds") List<Long> roomIds);

}
