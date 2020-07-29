package com.anixe.assignment.controller;

import com.anixe.assignment.config.ApplicationConfiguration;
import com.anixe.assignment.model.dto.CustomerHotelsResponse;
import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Hotel;
import com.anixe.assignment.service.BookingService;
import com.anixe.assignment.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller that manipulates hotels
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = ApplicationConfiguration.HOTEL_REST_URL)
public class HotelRestController {

    private final HotelService hotelService;
    private final BookingService bookingService;

    /**
     * Rest endpoint that returns all the hotels that a particular “surname” has made bookings for
     * @param surname String
     * @return CustomerHotelsResponse
     */
    @GetMapping
    public CustomerHotelsResponse getHotelsBookedByCustomer(@RequestParam(name = "surname") String surname) {

        return new CustomerHotelsResponse(hotelService.getHotelsBookedByCustomer(surname));
    }

    /**
     * Rest endpoint that returns
     * @param hotelId String
     * @return String
     */
    @GetMapping(path = "/{hotel-id}/totalAmount")
    public String getTotalAmountForBookings(@PathVariable("hotel-id") String hotelId) {
        return bookingService.getTotalAmountForHotel(hotelId);
    }

    /**
     * Rest endpoint that inserts a new hotel in the database
     * @param hotelDto HotelDto
     * @return Hotel
     */
    @PostMapping
    public Hotel addNewHotel(@RequestBody HotelDto hotelDto) {

        return hotelService.insertHotel(hotelDto);
    }

}
