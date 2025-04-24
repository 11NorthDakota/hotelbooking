package by.northdakota.booking_backend.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class GuestInfo {
    private int numOfAdults;
    private int numOfChildren;

    public int getTotalGuests() {
        return numOfAdults + numOfChildren;
    }
}