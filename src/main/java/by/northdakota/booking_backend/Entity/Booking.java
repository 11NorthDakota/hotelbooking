package by.northdakota.booking_backend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingRoom> bookingRooms = new ArrayList<>();
    @Embedded
    private GuestInfo guestInfo;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}