package com.anixe.assignment.mapper;

import com.anixe.assignment.model.dto.BookingDto;
import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Slf4j
public abstract class BookingEntityToDtoMapper {

    @Autowired
    private HotelEntityToDtoMapper hotelMapper;

    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "customerSurname", source = "customerSurname")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "paxNumber", source = "paxNumber")
    @Mapping(target = "hotelDto", source = "hotel", qualifiedByName = "mapHotel")
    public abstract BookingDto mapToBookingDto(Booking booking);

    @Named("mapHotel")
    HotelDto getHotel(Hotel hotel) {
        return hotelMapper.mapToHotelDto(hotel);
    }
}
