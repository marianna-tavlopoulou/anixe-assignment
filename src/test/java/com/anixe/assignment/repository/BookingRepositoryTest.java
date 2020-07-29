package com.anixe.assignment.repository;

import com.anixe.assignment.InitializationHelper;
import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookingRepositoryTest {

    private static Hotel hotel;
    private static Booking booking;
    @Autowired
    private BookingRepository repository;

    /**
     * Sets Up the test class
     */
    @Before
    public void setUp() {
        hotel = InitializationHelper.generateHotelNoBookings();
        booking = InitializationHelper.generateBooking(hotel);
    }

    @Test
    public void getSumOfPRiceAmountForGivenHotel() {
        Booking booking2 = booking;
        booking2.setAmount(new BigDecimal(235));
        repository.save(booking);
        repository.save(booking2);
        Assert.assertEquals(booking2.getAmount().add(booking.getAmount()), repository.getSumOfPRiceAmountForGivenHotel(hotel));
    }

    @Test(expected = Exception.class)
    public void nullHotelThrowsException() {
        Booking booking2 = booking;
        booking2.setHotel(null);
        repository.save(booking2);
    }

}