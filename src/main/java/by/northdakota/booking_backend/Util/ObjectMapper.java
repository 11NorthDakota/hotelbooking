package by.northdakota.booking_backend.Util;


import by.northdakota.booking_backend.Dto.*;
import by.northdakota.booking_backend.Entity.*;
import by.northdakota.booking_backend.Repository.HotelRepository;
import by.northdakota.booking_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectMapper {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public RoomDto roomToDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getRoomType(),
                room.getPrice(),
                room.getRoomPhotoUrl(),
                room.getRoomDescription()
        );
    }

    public Room dtoToRoom(RoomDto roomDto,Hotel hotel){
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setRoomType(roomDto.getRoomType());
        room.setPrice(roomDto.getPrice());
        room.setRoomPhotoUrl(roomDto.getRoomPhotoUrl());
        room.setRoomDescription(roomDto.getRoomDescription());
        room.setHotel(hotel);
        return room;
    }



    public HotelDto hotelToDto(Hotel hotel) {
        return new HotelDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getLocation(),
                hotel.getDescription(),
                hotel.getRooms().stream().map(
                        this::roomToDto
                ).toList()
        );
    }

    public BookingDto bookingToDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getUser().getId(),
                booking.getBookingRooms().stream()
                        .map(br -> br.getRoom().getId())
                        .collect(Collectors.toList()),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getStatus().name()
        );
    }

    public Hotel dtoToHotel(HotelDto dto){

        Hotel hotel = new Hotel(
                null,
                dto.getHotelName(),
                dto.getLocation(),
                dto.getDescription(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        List<Room> rooms = dto.getRooms().stream().map(roomDto->dtoToRoom(roomDto,hotel)).toList();
        hotel.setRooms(rooms);
        return hotel;
    }

    public ReviewDto reviewToDto(Review review){
        return new ReviewDto(
                review.getId(),
                review.getMessage(),
                review.getUser().getId(),
                review.getHotel().getId()
        );
    }
    public Review dtoToReview(ReviewDto dto){
        Review review = new Review();
        review.setId(dto.getId());
        review.setMessage(dto.getMessage());
        review.setUser(userRepository.findById(dto.getUser_id()).get());
        review.setHotel(hotelRepository.findById(dto.getHotel_id()).get());
        return review;
    }

    public UserDto userToDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getReviews().stream().map(
                        this::reviewToDto
                ).toList(),
                user.getRole().stream().map(
                        Role::getName
                ).toList()
        );
    }

}
