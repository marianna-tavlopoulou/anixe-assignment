package com.anixe.assignment.mapper;

import com.anixe.assignment.model.dto.BookingDto;
import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import com.anixe.assignment.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Slf4j
public abstract class DtoToBookingEntityMapper {

    @Autowired
    private DtoToHotelEntityMapper hotelMapper;
    @Autowired
    private HotelRepository hotelRepository;

    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "customerSurname", source = "customerSurname")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "paxNumber", source = "paxNumber")
    @Mapping(target = "hotel", source = "hotelDto", qualifiedByName = "mapHotel")
    public abstract Booking mapToHotelDto(BookingDto bookingDto);

    @Named("mapHotel")
    Hotel getHotel(HotelDto hotelDto) {
        Optional<Hotel> hotelOptional = hotelRepository.getById(hotelDto.getId());
        if (hotelOptional.isPresent()) {
            return hotelOptional.get();
        } else {
            Hotel hotel = hotelRepository.save(hotelMapper.mapToHotelDto(hotelDto));
            hotelRepository.flush();
            return hotel;
        }
    }
}
