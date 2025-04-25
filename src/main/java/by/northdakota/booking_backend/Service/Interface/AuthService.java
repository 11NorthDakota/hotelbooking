package by.northdakota.booking_backend.Service.Interface;

import by.northdakota.booking_backend.Dto.LoginUserDto;
import by.northdakota.booking_backend.Dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> createAuthToken(@RequestBody LoginUserDto loginUserDto) throws Exception;
    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto);

}
