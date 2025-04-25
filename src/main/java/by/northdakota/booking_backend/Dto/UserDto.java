package by.northdakota.booking_backend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO пользователя")
public class UserDto {

    @Schema(description = "ID пользователя", example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", example = "john_doe")
    private String username;

    @Schema(description = "Email пользователя", example = "john@example.com")
    private String email;

    @Schema(description = "Отзывы пользователя")
    private List<ReviewDto> review;

    @Schema(description = "Роли пользователя", example = "[\"ROLE_USER\"]")
    private List<String> role;
    @Schema(description = "Номер пользователя", example = "+79515555505")
    private String phoneNumber;

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDto(Long id, String username, String email, List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}