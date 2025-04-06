package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Entity.User;
import by.northdakota.booking_backend.Exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById() throws NotFoundException;
    Long saveUser(User user);
    void deleteUser(Long id) throws NotFoundException;
}
