package by.northdakota.booking_backend.Entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private BigDecimal price;
    private String roomPhotoUrl;
    private String roomDescription;
}
