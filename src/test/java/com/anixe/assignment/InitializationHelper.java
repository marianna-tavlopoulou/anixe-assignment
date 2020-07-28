package com.anixe.assignment;

import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;

import java.math.BigDecimal;
import java.util.HashSet;

public class InitializationHelper {

    public static Hotel generateHotelNoBookings() {
        return Hotel.builder().name("Test Hotel").address("Test 3").starRating(4).bookings(new HashSet<>()).build();
    }

    public static Booking generateBooking(Hotel hotel) {
        return Booking.builder()
            .hotel(hotel)
            .customerName("TestName")
            .customerSurname("TestSurname")
            .amount(BigDecimal.valueOf(153.33))
            .currency("EUR")
            .paxNumber("222")
            .build();
    }
}
