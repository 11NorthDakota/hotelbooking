package by.northdakota.booking_backend.Repository;

import by.northdakota.booking_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {
}
