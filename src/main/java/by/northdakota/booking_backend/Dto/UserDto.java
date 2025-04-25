package by.northdakota.booking_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<ReviewDto> review;
    private List<String> role;

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDto(Long id, String username, String email,List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
