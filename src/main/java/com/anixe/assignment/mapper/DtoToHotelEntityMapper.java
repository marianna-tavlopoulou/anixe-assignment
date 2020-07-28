package com.anixe.assignment.mapper;

import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class DtoToHotelEntityMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "starRating", source = "starRating")
    @Mapping(target = "bookings", qualifiedByName = "getBookings")
    public abstract Hotel mapToHotelDto(HotelDto hotel);

}
