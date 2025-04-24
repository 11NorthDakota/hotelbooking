package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return null;
    }


}
