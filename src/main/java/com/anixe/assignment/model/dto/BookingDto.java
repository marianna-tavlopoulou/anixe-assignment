package com.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

/**
 * Data transfer object that holds information for bookings
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class BookingDto {
    private HotelDto hotelDto;
    private String customerName;
    private String customerSurname;
    private String paxNumber;
    private BigDecimal amount;
    private String currency;

}
