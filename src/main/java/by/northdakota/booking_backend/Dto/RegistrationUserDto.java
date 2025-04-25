package by.northdakota.booking_backend.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationUserDto {

    private Long id;
    private String name;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;

}
