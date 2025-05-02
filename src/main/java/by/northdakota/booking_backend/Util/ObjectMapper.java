package by.northdakota.booking_backend.Util;


import by.northdakota.booking_backend.Dto.*;
import by.northdakota.booking_backend.Entity.*;
import by.northdakota.booking_backend.Repository.HotelRepository;
import by.northdakota.booking_backend.Repository.RoleRepository;
import by.northdakota.booking_backend.Repository.RoomRepository;
import by.northdakota.booking_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectMapper {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;

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
                booking.getStatus().name(),
                booking.getGuestInfo().getNumOfAdults(),
                booking.getGuestInfo().getNumOfChildren()
        );
    }

    public Booking dtoToBooking(BookingDto bookingDto){
        User user = userRepository.findById(bookingDto.getUserId()).get();

        Booking booking = new Booking();

        booking.setId(bookingDto.getId());
        booking.setUser(user);
        booking.setCheckInDate(bookingDto.getCheckInDate());
        booking.setCheckOutDate(bookingDto.getCheckOutDate());
        booking.setStatus(BookingStatus.valueOf(bookingDto.getStatus()));

        List<BookingRoom> bookingRooms = new ArrayList<>();
        if (bookingDto.getRoomIds() != null && !bookingDto.getRoomIds().isEmpty()) {
            for (Long roomId : bookingDto.getRoomIds()) {
                Room room = roomRepository.findById(roomId).get();
                BookingRoom bookingRoom = new BookingRoom();
                bookingRoom.setBooking(booking);
                bookingRoom.setRoom(room);
                bookingRooms.add(bookingRoom);
            }
        }
        booking.setBookingRooms(bookingRooms);
        GuestInfo gInfo = new GuestInfo();
        gInfo.setNumOfAdults(bookingDto.getNumOfAdults());
        gInfo.setNumOfChildren(bookingDto.getNumOfChildren());
        booking.setGuestInfo(gInfo);
        return booking;
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
        List<ReviewDto> review;
        if(user.getReviews() == null){
            review = new ArrayList<>();
        }
        else {
            review = user.getReviews().stream().map(
                    this::reviewToDto
            ).toList();
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                review,
                user.getRole().stream().map(
                        Role::getName
                ).toList(),
                user.getPhoneNumber()
        );
    }

    public User dtoToUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).get();
        user.setId(userDto.getId());
        user.setName(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());

        if (userDto.getRole() != null && !userDto.getRole().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            for (String roleName : userDto.getRole()) {
                Role role = roleRepository.findByName(roleName).get();
                roles.add(role);
            }
            user.setRole(roles);
        }

        if (userDto.getReview() != null && !userDto.getReview().isEmpty()) {
            List<Review> reviews = new ArrayList<>();
            for (ReviewDto reviewDto : userDto.getReview()) {
                Review review = dtoToReview(reviewDto);
                review.setUser(user);
                reviews.add(review);
            }
            user.setReviews(reviews);
        }

        return user;
    }
}
