package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.JwtResponse;
import by.northdakota.booking_backend.Dto.LoginUserDto;
import by.northdakota.booking_backend.Dto.RegistrationUserDto;
import by.northdakota.booking_backend.Dto.UserDto;
import by.northdakota.booking_backend.Exception.AppError;
import by.northdakota.booking_backend.Repository.UserRepository;
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
import by.northdakota.booking_backend.Entity.Role;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserDto loginUserDto) throws Exception {
        try{
            System.out.println(loginUserDto.getUsername());
            System.out.println(loginUserDto.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginUserDto.getUsername(), loginUserDto.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(loginUserDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        Collection<Role> role = userRepository.findByName(loginUserDto.getUsername()).get().getRole();
        return new ResponseEntity<>(new JwtResponse(token,role), HttpStatus.OK);
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        System.out.println(registrationUserDto.getPassword());
        System.out.println(registrationUserDto.getConfirmPassword());
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"пароли не совпали"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.findByUsername(registrationUserDto.getName()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"Пользователь существует"),
                    HttpStatus.BAD_REQUEST);
        }
        UserDto userDto = (UserDto) userService.createUser(registrationUserDto).getBody();
        return ResponseEntity.ok(userDto);
    }

}
