package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<?> getUserById(Long userId);
    ResponseEntity<?> saveUser(User user);
    ResponseEntity<?> blockUser(Long userId);
    ResponseEntity<?> unblockUser(Long userId);
}
