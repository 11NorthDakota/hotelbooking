package by.northdakota.booking_backend.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    @OneToMany
    @JoinColumn()
    private List<Room> rooms;



}
