package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {


}
