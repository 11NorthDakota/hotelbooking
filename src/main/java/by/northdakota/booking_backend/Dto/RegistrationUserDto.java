package by.northdakota.booking_backend.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO регистрации пользователя")
public class RegistrationUserDto {


    @Schema(description = "Имя пользователя", example = "john_doe")
    private String name;

    @Schema(description = "Пароль", example = "P@ssw0rd!")
    private String password;

    @Schema(description = "Подтверждение пароля", example = "P@ssw0rd!")
    private String confirmPassword;

    @Schema(description = "Email", example = "john@example.com")
    private String email;

    @Schema(description = "Номер телефона", example = "+79161234567")
    private String phoneNumber;
}