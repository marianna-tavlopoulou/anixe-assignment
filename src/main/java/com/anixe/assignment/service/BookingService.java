package com.anixe.assignment.service;

import com.anixe.assignment.mapper.BookingEntityToDtoMapper;
import com.anixe.assignment.mapper.DtoToBookingEntityMapper;
import com.anixe.assignment.model.dto.BookingDto;
import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import com.anixe.assignment.repository.BookingRepository;
import com.anixe.assignment.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final DtoToBookingEntityMapper bookingMapper;
    private final BookingEntityToDtoMapper bookingEntityToDtoMapper;

    /**
     * Inserts a new booking for a hotel. If hotel does not exist create a new one
     * @param bookingDto BookingDto
     * @return BookingDto
     */
    public BookingDto createBooking(BookingDto bookingDto) {
        Assert.notNull(bookingDto, "Please provide booking information");
        Assert.notNull(bookingDto.getHotelDto(), "Hotel details may not be empty");
        Booking booking = bookingRepository.save(bookingMapper.mapToHotelDto(bookingDto));
        return bookingEntityToDtoMapper.mapToBookingDto(booking);

    }

    /**
     * Gets all bookings total price amount for a specific hotel
     * @param id String
     * @return String
     */
    public String getTotalAmountForHotel(String id) {
        Optional<Hotel> hotelOptional = hotelRepository.getById(id);
        if (hotelOptional.isPresent()) {
            return bookingRepository.getSumOfPRiceAmountForGivenHotel(hotelOptional.get()).toString();
        }
        return "No hotel found with given id";
    }

    /**
     * Gets all bookings for a specific hotel
     * @param id String
     * @return List<BookingDto>
     */
    public List<BookingDto> getAllHotelBookings(String id) {
        Optional<Hotel> hotelOptional = hotelRepository.getById(id);
        return hotelOptional.map(
            hotel -> hotel.getBookings().stream().map(bookingEntityToDtoMapper::mapToBookingDto).collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }
}
