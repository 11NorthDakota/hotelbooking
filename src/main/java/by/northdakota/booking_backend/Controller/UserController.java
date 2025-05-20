package by.northdakota.booking_backend.Controller;

import by.northdakota.booking_backend.Dto.UserDto;
import by.northdakota.booking_backend.Dto.UserUpdateDto;
import by.northdakota.booking_backend.Service.Interface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Методы для управления пользователями")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить список всех пользователей")
    @ApiResponse(responseCode = "200", description = "Список пользователей получен успешно")
    @GetMapping
    public ResponseEntity<?> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Пользователь найден")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Заблокировать пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Пользователь заблокирован")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @PutMapping("/{userId}/block")
    public ResponseEntity<?> blockUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long userId) {
        return userService.blockUser(userId);
    }

    @Operation(summary = "Разблокировать пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Пользователь разблокирован")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @PutMapping("/{userId}/unblock")
    public ResponseEntity<?> unblockUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long userId) {
        return userService.unblockUser(userId);
    }

    @GetMapping("/current_user/{token}")
    public ResponseEntity<?> getActiveUser(@PathVariable String token){
        return userService.getActiveUser(token);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @PutMapping("/change/{userId}")
    public ResponseEntity<?> changeUser(@PathVariable Long userId, @RequestBody UserUpdateDto userDto){
        return userService.updateUser(userId, userDto);
    }

}

