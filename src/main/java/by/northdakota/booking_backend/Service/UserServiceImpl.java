package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Dto.RegistrationUserDto;
import by.northdakota.booking_backend.Dto.UserDto;
import by.northdakota.booking_backend.Dto.UserUpdateDto;
import by.northdakota.booking_backend.Entity.User;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.UserRepository;
import by.northdakota.booking_backend.Service.Interface.UserService;
import by.northdakota.booking_backend.Util.JwtTokenUtil;
import by.northdakota.booking_backend.Util.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleServiceImpl roleServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;
    private final JwtTokenUtil jwtTokenUtil;

    public Optional<User> findByUsername(String username){
        return userRepository.findByName(username);
    }

    @Transactional
    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = users.stream().map(mapper::userToDto).toList();
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<?> getUserById(Long userId) {
        if(!userRepository.existsById(userId)) return new ResponseEntity<>(new NotFoundException("user not found"),HttpStatus.NOT_FOUND);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("empty data"),HttpStatus.I_AM_A_TEAPOT);
        }
        UserDto userDto = mapper.userToDto(user.get());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<?> createUser(RegistrationUserDto registrationUserDto) {
        if(userRepository.findByName(registrationUserDto.getName()).isPresent()){
            return new ResponseEntity<>(new AlreadyExistsException("username already exists"),HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setName(registrationUserDto.getName());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setEmail(registrationUserDto.getEmail());
        user.setPhoneNumber(registrationUserDto.getPhoneNumber());
        user.setRole(List.of(roleServiceImpl.getUserRole()));
        user = userRepository.save(user);
        UserDto userDto = mapper.userToDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @Transactional
    public ResponseEntity<?> getActiveUser(String token){
       String username = jwtTokenUtil.getUsername(token);
       User user = userRepository.findByName(username).get();
       UserDto userDto = mapper.userToDto(user);
       return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<?> blockUser(Long userId) {
       if(!userRepository.existsById(userId)) {
           return new ResponseEntity<>(new NotFoundException("user not found"),HttpStatus.NOT_FOUND);
       }
       Optional<User> userOptional = userRepository.findById(userId);
       if(userOptional.isEmpty()){
           return new ResponseEntity<>(new NotFoundException("empty data"),HttpStatus.I_AM_A_TEAPOT);
       }

       User user =  userOptional.get();
       user.setBlocked(true);
       user = userRepository.save(user);
       UserDto userDto = mapper.userToDto(user);
       return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<?> unblockUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            return new ResponseEntity<>(new NotFoundException("user not found"),HttpStatus.NOT_FOUND);
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("empty data"),HttpStatus.I_AM_A_TEAPOT);
        }

        User user =  userOptional.get();
        user.setBlocked(false);
        user = userRepository.save(user);
        UserDto userDto = mapper.userToDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<?> deleteUser(Long userId){
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @Override
    public ResponseEntity<?> updateUser(Long userId, UserUpdateDto userDto){
        User user = userRepository.findById(userId).get();
        user.setName(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = mapper.userToDto(updatedUser);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found",username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRole().stream()
                        .map(role->new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }


}
