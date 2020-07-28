package com.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class HotelDto {

    private String id;
    private String name;
    private String address;
    private int starRating;
}
