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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = ApplicationConfiguration.HOTEL_REST_URL)
public class HotelRestController {

    private final HotelService hotelService;
    private final BookingService bookingService;

    @GetMapping
    public CustomerHotelsResponse fetchByReferenceId(@RequestParam(name = "customer-surname") String surname) {

        return new CustomerHotelsResponse(hotelService.getHotelsBookedByCustomer(surname));
    }

    @GetMapping(path = "/{hotel-id}/totalAmount")
    public String getTotalAmountForBookings(@PathVariable("hotel-id") String hotelId) {
        return bookingService.getTotalAmountForHotel(hotelId);
    }

    @PostMapping
    public Hotel addNewHotel(@RequestBody HotelDto hotelDto) {

        return hotelService.insertHotel(hotelDto);
    }

}
