package by.northdakota.booking_backend.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class GuestInfo {
    private Integer numOfAdults;
    private Integer numOfChildren;

    public int getTotalGuests() {
        return numOfAdults + numOfChildren;
    }
}