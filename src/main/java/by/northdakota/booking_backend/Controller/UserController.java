package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Entity.User;
import by.northdakota.booking_backend.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long userId) {
        return userService.blockUser(userId);
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId) {
        return userService.unblockUser(userId);
    }

}
