package by.northdakota.booking_backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    private String message;

}
