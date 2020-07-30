package com.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * Data transfer object that holds a list of HotelDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerHotelsResponse {

    private List<HotelDto> hotelDtoList;


}
