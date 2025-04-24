package by.northdakota.booking_backend.Util;

import by.northdakota.booking_backend.Dto.BookingDto;
import by.northdakota.booking_backend.Dto.HotelDto;
import by.northdakota.booking_backend.Dto.RoomDto;
import by.northdakota.booking_backend.Entity.Booking;
import by.northdakota.booking_backend.Entity.Hotel;
import by.northdakota.booking_backend.Entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

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


}
