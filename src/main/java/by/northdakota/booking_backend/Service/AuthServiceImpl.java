package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.JwtResponse;
import by.northdakota.booking_backend.Dto.LoginUserDto;
import by.northdakota.booking_backend.Dto.RegistrationUserDto;
import by.northdakota.booking_backend.Dto.UserDto;
import by.northdakota.booking_backend.Exception.AppError;
import by.northdakota.booking_backend.Service.Interface.AuthService;
import by.northdakota.booking_backend.Service.Interface.UserService;
import by.northdakota.booking_backend.Util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import by.northdakota.booking_backend.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserDto loginUserDto) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginUserDto.getUsername(), loginUserDto.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(loginUserDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if(registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"пароли не совпали"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.findByUsername(registrationUserDto.getName()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"Пользователь существует"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = (User) userService.createUser(registrationUserDto).getBody();
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return ResponseEntity.ok(userDto);
    }

}
