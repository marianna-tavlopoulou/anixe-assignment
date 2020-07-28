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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final DtoToBookingEntityMapper bookingMapper;
    private final BookingEntityToDtoMapper bookingEntityToDtoMapper;


    public BookingDto createBooking(BookingDto bookingDto) {

        Booking booking = bookingRepository.save(bookingMapper.mapToHotelDto(bookingDto));
        return bookingEntityToDtoMapper.mapToBookingDto(booking);

    }

    public String getTotalAmountForHotel(String id) {
        Optional<Hotel> hotelOptional = hotelRepository.getById(id);
        if (hotelOptional.isPresent()) {
            return bookingRepository.getSumOfPRiceAmountForGivenHotel(hotelOptional.get()).toString();
        }
        return "No hotel found with given id";
    }

    public List<BookingDto> getAllHotelBookings(String id) {
        Optional<Hotel> hotelOptional = hotelRepository.getById(id);
        return hotelOptional.map(hotel -> bookingRepository.getAllByHotel(hotel)
            .stream()
            .map(bookingEntityToDtoMapper::mapToBookingDto)
            .collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
