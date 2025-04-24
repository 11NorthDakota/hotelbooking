package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Entity.User;
import by.northdakota.booking_backend.Exception.AlreadyExistsException;
import by.northdakota.booking_backend.Exception.NotFoundException;
import by.northdakota.booking_backend.Repository.ReviewRepository;
import by.northdakota.booking_backend.Repository.UserRepository;
import by.northdakota.booking_backend.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(Long userId) {
        if(!userRepository.existsById(userId)) return new ResponseEntity<>(new NotFoundException("user not found"),HttpStatus.NOT_FOUND);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>(new NotFoundException("empty data"),HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveUser(User user) {
        // Для нового пользователя устанавливаем ID в null
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return new ResponseEntity<>(new AlreadyExistsException("user already exists"), HttpStatus.CONFLICT);
        }

        // Для гарантированного создания нового пользователя
        user.setId(null);
        user.setReviews(new ArrayList<>()); // Инициализируем пустой список

        User savedUser = userRepository.save(user);

        // Возвращаем сохраненного пользователя
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Используем статус 201 Created
    }

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
       userRepository.save(user);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

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
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
