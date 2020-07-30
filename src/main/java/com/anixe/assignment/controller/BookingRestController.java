package com.anixe.assignment.controller;

import com.anixe.assignment.config.ApplicationConfiguration;
import com.anixe.assignment.model.dto.BookingDto;
import com.anixe.assignment.model.dto.BookingResponse;
import com.anixe.assignment.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller that manipulates bookings
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = ApplicationConfiguration.BOOKING_REST_URL)
public class BookingRestController {

    private final BookingService bookingService;

    /**
     * POST endpoint that inserts a new booking for a hotel. If the hotel does not exist we insert it.
     * @param bookingDto BookingDto
     * @return BookingDto
     */
    @PostMapping
    public BookingDto addNewBooking(@RequestBody BookingDto bookingDto) {

        return bookingService.createBooking(bookingDto);
    }

    /**
     * Rest endpoint that returns all the bookings that a particular hotel has
     * @param hotelId String
     * @return BookingResponse
     */
    @GetMapping
    public BookingResponse getBookings(@RequestParam("hotel-id") String hotelId) {

        return BookingResponse.builder().bookings(bookingService.getAllHotelBookings(hotelId)).build();
    }
}
