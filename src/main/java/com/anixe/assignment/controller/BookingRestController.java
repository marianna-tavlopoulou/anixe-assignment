package com.anixe.assignment.controller;

import com.anixe.assignment.config.ApplicationConfiguration;
import com.anixe.assignment.model.dto.BookingDto;
import com.anixe.assignment.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = ApplicationConfiguration.BOOKING_REST_URL)
public class BookingRestController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto addNewBooking(@RequestBody BookingDto bookingDto) {

        return bookingService.createBooking(bookingDto);
    }

    @GetMapping
    public List<BookingDto> getBookings(@RequestParam("hotel-id") String hotelId) {

        return bookingService.getAllHotelBookings(hotelId);
    }
}
