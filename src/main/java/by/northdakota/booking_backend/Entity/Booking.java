package by.northdakota.booking_backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="bookings")
@Data
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
    private List<Room> rooms;
    @Embedded
    private GuestInfo guestInfo;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String bookingConfirmCode;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}