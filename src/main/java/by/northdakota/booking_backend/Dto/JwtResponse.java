package by.northdakota.booking_backend.Dto;

import by.northdakota.booking_backend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private Collection<Role> role;

}
