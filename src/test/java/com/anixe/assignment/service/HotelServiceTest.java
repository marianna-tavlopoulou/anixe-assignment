package com.anixe.assignment.service;

import com.anixe.assignment.InitializationHelper;
import com.anixe.assignment.mapper.DtoToHotelEntityMapper;
import com.anixe.assignment.mapper.HotelEntityToDtoMapper;
import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import com.anixe.assignment.repository.HotelRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelEntityToDtoMapper hotelMapper;
    @Mock
    private DtoToHotelEntityMapper hotelDtoMapper;
    @InjectMocks
    private HotelService service;

    private static Hotel hotel;
    private static Booking booking;

    /**
     * Sets Up the test class
     */
    @Before
    public void setUp() {
        hotel = InitializationHelper.generateHotelNoBookings();
        booking = InitializationHelper.generateBooking(hotel);
    }

    @Test
    public void getHotelsBookedByCustomer() {
        Hotel hotel = InitializationHelper.generateHotelNoBookings();
        Booking booking = InitializationHelper.generateBooking(hotel);
        HotelDto hotelDto = HotelDto.builder().id("000").name("TestHotel").address("Test 3").starRating(4).build();
        Mockito.doReturn(hotelDto).when(hotelMapper).mapToHotelDto(hotel);
        Mockito.doReturn(hotel).when(hotelDtoMapper).mapToHotelDto(hotelDto);
        Mockito.doReturn(Collections.singleton(hotel))
            .when(hotelRepository)
            .getHotelsBookedByCustomer(booking.getCustomerSurname());
        List<HotelDto> hotelDtos = service.getHotelsBookedByCustomer(booking.getCustomerSurname());
        Mockito.verify(hotelRepository, Mockito.times(1)).getHotelsBookedByCustomer(booking.getCustomerSurname());
        Mockito.verify(hotelMapper, Mockito.times(1)).mapToHotelDto(hotel);
        Assert.assertFalse(hotelDtos.isEmpty());
        Assert.assertEquals(1, hotelDtos.size());
    }

    @Test
    public void insertHotel() {
        HotelDto hotelDto = HotelDto.builder()
            .id(hotel.getId())
            .name(hotel.getName())
            .address(hotel.getAddress())
            .starRating(hotel.getStarRating())
            .build();
        Mockito.doReturn(hotel).when(hotelRepository).save(hotel);
        Mockito.doReturn(hotel).when(hotelDtoMapper).mapToHotelDto(hotelDto);
        Hotel newHotel = service.insertHotel(hotelDto);
        Assert.assertEquals(hotelDto.getName(), newHotel.getName());
        Assert.assertEquals(hotelDto.getAddress(), newHotel.getAddress());
        Assert.assertEquals(hotelDto.getStarRating(), newHotel.getStarRating());
    }
}