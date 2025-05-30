package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Dto.RegistrationUserDto;
import by.northdakota.booking_backend.Dto.UserDto;
import by.northdakota.booking_backend.Dto.UserUpdateDto;
import by.northdakota.booking_backend.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    ResponseEntity<?>  createUser(RegistrationUserDto registrationUserDto);
    Optional<User> findByUsername(String username);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getUserById(Long userId);
    ResponseEntity<?> blockUser(Long userId);
    ResponseEntity<?> unblockUser(Long userId);
    ResponseEntity<?> getActiveUser(String token);
    ResponseEntity<?> deleteUser(Long userId);
    ResponseEntity<?> updateUser(Long userId, UserUpdateDto userDto);
}
