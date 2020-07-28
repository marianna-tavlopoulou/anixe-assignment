package com.anixe.assignment.service;

import com.anixe.assignment.mapper.DtoToHotelEntityMapper;
import com.anixe.assignment.mapper.HotelEntityToDtoMapper;
import com.anixe.assignment.model.dto.HotelDto;
import com.anixe.assignment.model.entity.Hotel;
import com.anixe.assignment.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelEntityToDtoMapper hotelMapper;
    private final DtoToHotelEntityMapper hotelDtoMapper;

    public List<HotelDto> getHotelsBookedByCustomer(String surname) {

        return hotelRepository.getHotelsBookedByCustomer(surname)
            .stream()
            .map(hotelMapper::mapToHotelDto)
            .collect(Collectors.toList());
    }

    public Hotel insertHotel(HotelDto hotelDto) {
        return hotelRepository.save(hotelDtoMapper.mapToHotelDto(hotelDto));
    }
}
